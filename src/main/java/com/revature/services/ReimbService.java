package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbService {
	ReimbDAOImpl reimbDAO = new ReimbDAOImpl();
	
	public List<Reimbursement> getAllReimbursements(){
		return reimbDAO.getAllReimbursements();
	}
	
	public Reimbursement addReimbursement(Reimbursement updated) {
		Reimbursement newReimb = new Reimbursement();	
		if (reimbDAO.getReimbursementById(updated.getId()) == null) {
			newReimb = reimbDAO.addReimbursement(updated);
		}
		return newReimb;
	}
	
	public boolean deleteReimbursement(int id) {
		boolean isDeleted = reimbDAO.deleteReimbursement(id);
		if (isDeleted) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Reimbursement> getReimbursementsByAuthor(int id) {
		List<Reimbursement> tickets = new ArrayList<Reimbursement>();
		
		tickets = reimbDAO.getReimbursementsByAuthor(id);
		
		
		return tickets;
	}
}
