package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("in LoginServlet.doPost()");
		
		UserService userService = new UserService();
		ObjectMapper mapper = new ObjectMapper();
		
		String[] userCredentials = mapper.readValue(request.getInputStream(), String[].class);
		String username = userCredentials[0];
		String password = userCredentials[1];
		
		User authUser = userService.loginUser(username, password);
		
		// Associate user with this session
		if (authUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", authUser);
		}
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		
		String authUserJSON = mapper.writeValueAsString(authUser);
		pw.write(authUserJSON);
	}
}
