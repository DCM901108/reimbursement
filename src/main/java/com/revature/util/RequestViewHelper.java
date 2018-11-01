package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestViewHelper {
	
	public static String process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
			case "/reimbursement-app/login.view":
				return "partials/login.html";
			case "/reimbursement-app/register.view":
				return "partials/register.html";
			case "/reimbursement-app/home.view":
				return "partials/home.html";
			default:
				return null;
		}
	}
}
