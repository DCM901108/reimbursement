package com.revature.services;

import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	UserDAOImpl userDAO = new UserDAOImpl();
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}
	
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	// Take in User as parameter, check email and password, 
	public User addUser(User newUser) {
		System.out.println("in addUser()");
		
		boolean emailAvailable = true;
		boolean usernameAvailable = true;
		
		System.out.println("in addUser() past booleans" +" email boolean availability " + emailAvailable +" username boolean availability " + usernameAvailable);
		
		if(emailAvailable && usernameAvailable) {
			newUser = userDAO.addUser(newUser);		
			//for(User user : userDAO.getAllUsers()) System.out.println(user);
		}
		else
			newUser = null;
		
		return newUser;
	}
	
//	public User updateUser(User updatedUser) {
//		
//		List<User> users = userDAO.getAllUsers();
//		
//		for(User user : users)
//			if(updatedUser.getErs_users_id() == user.getErs_users_id())
//				return userDAO.updateUser(updatedUser);
//		
//		return null;
//		
//	}
	
	public boolean deleteUser(int userId) {
		return  false;
	}
	
	@SuppressWarnings("unused")
	public boolean isEmailAvailable(String emailAddress) {
		System.out.println("it is currently here  in  for email ");
		
		User u = userDAO.getUserByEmailAddress(emailAddress);
		
		System.out.println("it is currently here  in email " + " " + u.toString());
		
		if(u.getErs_username() == null) {
			return true;
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	public boolean isUsernameAvailable(String username) {
		System.out.println("it is currently here  in service " + username);
		User u = userDAO.getUserByUsername(username);
		System.out.println(u);
		if(u.getErs_username() == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public User loginUser(String username, String password) {
		System.out.println("Made it to loginUser");
		User u = new User();
		//System.out.println(u.toString());
		u = userDAO.getUserByCredentials(username, password);
		//System.out.println(u.toString());
		return u;
	}
}
