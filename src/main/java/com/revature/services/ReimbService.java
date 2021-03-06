package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbService {
	ReimbDAOImpl reimbDAO = new ReimbDAOImpl();
	
	public ArrayList<Reimbursement> getAllReimbursements(){
		return reimbDAO.getAllReimbursements();
	}
	
	public boolean addReimbursement(Reimbursement updated) {
		boolean rowAdded = false;

		rowAdded = reimbDAO.addReimbursement(updated);
		
		return rowAdded;
	}
	
	public boolean deleteReimbursement(int id) {
		boolean isDeleted = reimbDAO.deleteReimbursement(id);
		if (isDeleted) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Reimbursement> getReimbursementsByAuthor(int id) {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();
		tickets = reimbDAO.getReimbursementsByAuthor(id);		
		return tickets;
	}
	public boolean updateReimbursment(int a, int b, int c) {
		boolean isUpdated = reimbDAO.updateReimbursement(a, b, c);
		if (isUpdated){
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Reimbursement> ViewAllReimb() {
		// TODO Auto-generated method stub
		return null;
	}
}
