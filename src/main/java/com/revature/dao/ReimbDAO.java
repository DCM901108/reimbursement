package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbDAO {
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getReimbursementsByAuthor(int id);
	List<Reimbursement> getReimbursementsByStatus(int status);
	Reimbursement getReimbursementById(int id);
	Reimbursement addReimbursement(Reimbursement reimb);
	boolean updateReimbursement(int id);
	boolean deleteReimbursement(int id);
}
