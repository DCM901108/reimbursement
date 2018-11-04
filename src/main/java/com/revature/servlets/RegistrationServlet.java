package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in RegistrationServlet.doPost()");
		
		UserService userService = new UserService();
		ObjectMapper mapper = new ObjectMapper();
		
		User newUser = mapper.readValue(request.getInputStream(), User.class);
		System.out.println("Got to RegistrationServlet");
		System.out.println(newUser);
		
		// If no match is found, create new User
		newUser = userService.addUser(newUser);
		// else return some error message or code
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		
		String userJSON = mapper.writeValueAsString(newUser);
		pw.write(userJSON);
	}
}
