package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	List<User> getAllUsers();
	User getUserById(int userId);
	User getUserByCredentials(String username, String password);
	User addUser(User newUser);
	User getUserByUsername(String userName);
	User getUserByEmailAddress(String emailAddress);
}
