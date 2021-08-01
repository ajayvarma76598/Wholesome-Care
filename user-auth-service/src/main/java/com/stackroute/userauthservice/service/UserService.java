package com.stackroute.userauthservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.stackroute.userauthservice.dto.LocalUser;
import com.stackroute.userauthservice.dto.SignUpRequest;
import com.stackroute.userauthservice.exception.UserAlreadyExistAuthenticationException;
import com.stackroute.userauthservice.model.UserAuth;

public interface UserService {

	public UserAuth registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	UserAuth findUserByEmail(String email);

	Optional<UserAuth> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
