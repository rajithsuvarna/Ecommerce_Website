package com.rajith.service;

import java.util.List;

import com.rajith.exception.UserException;
import com.rajith.model.User;

public interface UserService {
	
	public User findUserById(Long userid) throws UserException;
	
	public User findUserProfileByJWT(String jwt) throws UserException;

	public List<User> findAllUsers();

}
