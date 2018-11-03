package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbDAO {
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getReimbursementsByAuthor(int id);
	List<Reimbursement> getReimbursementsByStatus(int status);
	boolean updateReimbursement(int id);
	boolean deleteReimbursement(int id);
}
