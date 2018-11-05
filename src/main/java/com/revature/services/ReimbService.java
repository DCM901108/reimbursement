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
	
<<<<<<< HEAD
	public Reimbursement addReimbursement(Reimbursement updated) {
		Reimbursement newReimb = new Reimbursement();	
		if (reimbDAO.addReimbursement(updated) == null) {
			newReimb = reimbDAO.addReimbursement(updated);
		}
		return newReimb;
	}
=======
//	public Reimbursement addReimbursement(Reimbursement updated) {
//		Reimbursement newReimb = new Reimbursement();	
//		if (reimbDAO.addReimbursement(updated.getId()) == null) {
//			newReimb = reimbDAO.addReimbursement(updated);
//		}
//		return newReimb;
//	}
>>>>>>> 6f0e0a707ef377d8f4e3c57e9a508a467578d210
	
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

	public ArrayList<Reimbursement> ViewAllReimb() {
		// TODO Auto-generated method stub
		return null;
	}
}
