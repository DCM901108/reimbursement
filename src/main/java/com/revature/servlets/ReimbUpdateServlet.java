package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

@WebServlet("/ReimbUpdateServlet")
public class ReimbUpdateServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("ReibUpdateServlet()");
		
		ReimbService reimbService = new ReimbService();
		ObjectMapper mapper = new ObjectMapper();
		
		
		//int id, int resolver, int status
		Integer[] userCredentials = mapper.readValue(request.getInputStream(), Integer[].class);
		Integer id = userCredentials[0];
		Integer resolver = userCredentials[1];
		Integer status = userCredentials[2];
		
		boolean b = reimbService.updateReimbursment(id, resolver, status);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String boolJSON = mapper.writeValueAsString(b);
		pw.write(boolJSON);
	}
	

}