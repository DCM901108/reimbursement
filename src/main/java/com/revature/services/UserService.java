package com.revature.services;

import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class UserService {
private UserDAO userDAO = new UserDAOImpl();
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}
	
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	public User addUser(User newUser) {
		boolean emailAvailable = isEmailAvailable(newUser.getEmailAddress());
		boolean usernameAvailable = isUsernameAvailable(newUser.getUsername());
		
		if(emailAvailable && usernameAvailable) {
			newUser = userDAO.addUser(newUser);
			for(User user : userDAO.getAllUsers()) System.out.println(user);
		}
		else
			newUser = null;
		
		return newUser;
	}
	
	public User updateUser(User updatedUser) {
		
		List<User> users = userDAO.getAllUsers();
		
		for(User user : users)
			if(updatedUser.getErs_users_id() == user.getId())
				return userDAO.updateUser(updatedUser);
		
		return null;
		
	}
	
	public boolean deleteUser(int userId) {
		return userDAO.deleteUser(userId);
	}
	
	public boolean isEmailAvailable(String emailAddress) {
		User u = userDAO.getUserByEmailAddress(emailAddress);
		
		if(u == null)
			return true;
		
		return false;
	}
	
	public boolean isUsernameAvailable(String username) {
		User u = userDAO.getUserByUsername(username);
		
		if(u == null)
			return true;
		
		return false;
	}
	
	public User loginUser(String username, String password) {
		return userDAO.getUserByCredentials(username, password);
	}
}
