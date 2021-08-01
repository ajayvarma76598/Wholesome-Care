package com.stackroute.userauthservice.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.stackroute.userauthservice.dto.LocalUser;
import com.stackroute.userauthservice.dto.SocialProvider;
import com.stackroute.userauthservice.dto.UserInfo;
import com.stackroute.userauthservice.model.Role;
import com.stackroute.userauthservice.model.UserAuth;


public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final String role) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
//		}
		return authorities;
	}

	public static SocialProvider toSocialProvider(String providerId) {
		for (SocialProvider socialProvider : SocialProvider.values()) {
			if (socialProvider.getProviderType().equals(providerId)) {
				return socialProvider;
			}
		}
		return SocialProvider.LOCAL;
	}

	public static UserInfo buildUserInfo(LocalUser localUser) {
//		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		UserAuth user = localUser.getUser();
		String role = user.getRole();
		return new UserInfo( user.getName(), user.getEmail(), role);
	}
}
