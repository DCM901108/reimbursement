package com.revature.services;

import java.util.ArrayList;

import com.revature.dao.ReimbDAO;
import com.revature.dao.ReimbDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbService 
{
	private ReimbDAO rd= new ReimbDAOImpl();
	
	public ArrayList<Reimbursement> ViewAllReimb()
	{
		return rd.getAllReimbursements();
	}
	
	
	
}
