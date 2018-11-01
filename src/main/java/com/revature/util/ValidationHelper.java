package com.revature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.UserService;

public class ValidationHelper {
	
	public static String process(HttpServletRequest request) throws IOException{
		
		UserService userService = new UserService();
		ObjectMapper mapper = new ObjectMapper();
		
		switch(request.getRequestURI()) {
			
			case "/reimbursement-app/username.validate":
				String username = mapper.readValue(request.getInputStream(), String.class);
				
				if (userService.isUsernameAvailable(username))
					return username;
				else return null;
				
			case "/reimbursement-app/email.validate":
				String emailAddress = mapper.readValue(request.getInputStream(), String.class);
				
				if(userService.isEmailAvailable(emailAddress)) 
					return emailAddress;
				else return null;
			default:
				return null;
		}
	}
}
