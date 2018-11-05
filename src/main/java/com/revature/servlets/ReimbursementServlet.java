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

@WebServlet('/tickets')
public class ReimbursementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReimbService reimbService = new ReimbService();
		ObjectMapper mapper = new ObjectMapper();
		
		Reimbursement newTicket = mapper.readValue(request.getInputStream(), Reimbursement.class);
		
		newTicket = reimbService.addReimbursement(newTicket);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String ticketJSON = mapper.writeValueAsString(newTicket);
		pw.write(ticketJSON);
	}
}
