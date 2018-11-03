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
				boolean b = userService.isUsernameAvailable(username);
				System.out.println("ValidationHelper value "+ b);
				if (b != false)
				{
					return username;
				}else {
					return null;
				}
				
			case "/reimbursement-app/email.validate":
				String emailAddress = mapper.readValue(request.getInputStream(), String.class);
				boolean b1 = userService.isEmailAvailable(emailAddress);
				if(b1 != false) 
				{
					return emailAddress;
				}else
				{
					return null;
				}
			default:
				return null;
		}
	}
}
