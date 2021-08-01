package com.stackroute.userauthservice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.stackroute.userauthservice.dto.LocalUser;
import com.stackroute.userauthservice.dto.SignUpRequest;
import com.stackroute.userauthservice.dto.SocialProvider;
import com.stackroute.userauthservice.exception.OAuth2AuthenticationProcessingException;
import com.stackroute.userauthservice.exception.UserAlreadyExistAuthenticationException;
import com.stackroute.userauthservice.model.Membership;
import com.stackroute.userauthservice.model.Role;
import com.stackroute.userauthservice.model.UserAuth;
import com.stackroute.userauthservice.repo.UserRepository;
import com.stackroute.userauthservice.security.oauth2.user.OAuth2UserInfo;
import com.stackroute.userauthservice.security.oauth2.user.OAuth2UserInfoFactory;
import com.stackroute.userauthservice.util.GeneralUtils;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(value = "transactionManager")
	public UserAuth registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
		if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
			throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}
		UserAuth user = buildUser(signUpRequest);
		user.setMembership(Membership.FREE.name());
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}
//googel auth
	private UserAuth buildUser(final SignUpRequest formDTO) {
		UserAuth user = new UserAuth();
		user.setName(formDTO.getDisplayName());
		user.setEmail(formDTO.getEmail());
		user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
//		final HashSet<Role> roles = new HashSet<Role>();
//		roles.add(roleRepository.findByName(Role.ROLE_USER));
		user.setRole(Role.ROLE_USER.name());
		user.setProvider(formDTO.getSocialProvider().getProviderType());
		return user;
	}

	@Override
	public UserAuth findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		UserAuth user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {
			if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private UserAuth updateExistingUser(UserAuth existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setName(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}

	private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
				.addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
	}

	@Override
	public Optional<UserAuth> findUserById(Long id) {
		return userRepository.findById(id);
	}
}
