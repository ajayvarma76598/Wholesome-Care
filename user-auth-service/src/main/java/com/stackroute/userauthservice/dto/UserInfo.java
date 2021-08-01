package com.stackroute.userauthservice.dto;

import java.util.List;

import lombok.Value;

@Value
public class UserInfo {
	private String displayName, email;
	private String role;
}