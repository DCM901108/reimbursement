package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

@WebServlet("/viewByAuthor")
public class ReimbViewByAuthor extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   
	{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ReimbService rs = new ReimbService();
		ObjectMapper m = new ObjectMapper();
		
		Integer author = m.readValue(request.getInputStream(), Integer.class);
		ArrayList<Reimbursement> getAll = rs.getReimbursementsByAuthor(author);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String getallJSON = m.writeValueAsString(getAll);
		pw.write(getallJSON);
	}
}
