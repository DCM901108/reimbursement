package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.RequestViewHelper;

public class LoadViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("in sdfsdf LoadViewServlet.doGet()");
		
		String nextView = RequestViewHelper.process(request);
		System.out.println(nextView);
		
		request.getRequestDispatcher(nextView).forward(request, response);
	}
}
