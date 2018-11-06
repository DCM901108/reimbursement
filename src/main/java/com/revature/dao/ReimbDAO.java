package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbDAO {
	ArrayList<Reimbursement> getAllReimbursements();
	ArrayList<Reimbursement> getReimbursementsByAuthor(int id);
	ArrayList<Reimbursement> getReimbursementsByStatus(int status);
	boolean updateReimbursement(int id, int status);
	boolean deleteReimbursement(int id);
	boolean addReimbursement(Reimbursement reimb);
}
