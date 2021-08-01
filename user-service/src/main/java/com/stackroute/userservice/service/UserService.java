package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {

	User registerUser(User user) throws UserAlreadyExistsException;
	User getUserByEmailId(String emailId) throws UserNotFoundException;
	User updateUserByEmailId(User user) throws UserNotFoundException;
}
