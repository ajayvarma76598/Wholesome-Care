package com.stackroute.userauthservice.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.stackroute.userauthservice.model.UserAuth;
import com.stackroute.userauthservice.util.GeneralUtils;


public class LocalUser extends User implements OAuth2User, OidcUser {


	private final OidcIdToken idToken;
	private final OidcUserInfo userInfo;
	private Map<String, Object> attributes;
	private UserAuth user;

	public LocalUser(final String userID, final String password,final String membership, final Collection<? extends GrantedAuthority> authorities, final UserAuth user) {
		this(userID, password,membership, authorities, user, null, null);
	}

	public LocalUser(final String userID, final String password, final String membership, final Collection<? extends GrantedAuthority> authorities, final UserAuth user, OidcIdToken idToken,
			OidcUserInfo userInfo) {
		super(userID, password, authorities);
		this.user = user;
		this.idToken = idToken;
		this.userInfo = userInfo;
	}

//	public LocalUser(String email, String password, String membership,
//			List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities, com.javachinna.model.User user2) {
//		// TODO Auto-generated constructor stub
//		super();
//	}

	public static LocalUser create(UserAuth user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(),user.getMembership(), GeneralUtils.buildSimpleGrantedAuthorities(user.getRole()),user, idToken, userInfo);
		localUser.setAttributes(attributes);
		return localUser;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return this.user.getName();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Map<String, Object> getClaims() {
		return this.attributes;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return this.userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		return this.idToken;
	}

	public com.stackroute.userauthservice.model.UserAuth getUser() {
		return user;
	}
}
