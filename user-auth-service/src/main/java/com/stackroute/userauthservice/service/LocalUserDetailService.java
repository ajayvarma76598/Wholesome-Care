package com.stackroute.userauthservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.userauthservice.dto.LocalUser;
import com.stackroute.userauthservice.exception.ResourceNotFoundException;
import com.stackroute.userauthservice.model.UserAuth;
import com.stackroute.userauthservice.util.GeneralUtils;


@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
		UserAuth user = userService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		return createLocalUser(user);
	}

	@Transactional
	public LocalUser loadUserById(Long id) {
		UserAuth user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return createLocalUser(user);
	}

	
	private LocalUser createLocalUser(UserAuth user) {
		return new LocalUser(user.getEmail(), user.getPassword(), user.getMembership(), GeneralUtils.buildSimpleGrantedAuthorities(user.getRole()), user);
	}
}
