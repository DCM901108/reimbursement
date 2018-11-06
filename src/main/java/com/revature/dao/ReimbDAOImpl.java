package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class ReimbDAOImpl implements ReimbDAO {
	
	@Override
	public ArrayList<Reimbursement> getAllReimbursements()
	{
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "SELECT * FROM ers_reimbursement";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) 
			{
				Reimbursement temp = new Reimbursement();
				
				temp.setId(rs.getInt("reimb_id"));
				temp.setAmount(rs.getInt("reimb_amount"));
				temp.setSubmitted(rs.getString("reimb_submitted"));
				temp.setResolved(rs.getString("reimb_resolved"));
				temp.setDescription(rs.getString("reimb_description"));
				temp.setReceipt(rs.getBlob("reimb_receipt"));
				temp.setAuthor(rs.getInt("reimb_author"));
				temp.setResolver(rs.getInt("reimb_resolver"));
				temp.setStatus(rs.getInt("reimb_status_id"));
				temp.setType(rs.getInt("reimb_type_id"));
				
				tickets.add(temp);
			}
			
			return tickets;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsByAuthor(int id) {
		
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author= ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				
				temp.setId(rs.getInt("reimb_id"));
				temp.setAmount(rs.getInt("reimb_amount"));
				temp.setSubmitted(rs.getString("reimb_submitted"));
				temp.setResolved(rs.getString("reimb_resolved"));
				temp.setDescription(rs.getString("reimb_description"));
				temp.setReceipt(rs.getBlob("reimb_receipt"));
				temp.setAuthor(rs.getInt("reimb_author"));
				temp.setResolver(rs.getInt("reimb_resolver"));
				temp.setStatus(rs.getInt("reimb_status_id"));
				temp.setType(rs.getInt("reimb_type_id"));
				
				tickets.add(temp);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return tickets;
	}

	@Override // This will be useless without the corresponding SQL procedure
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "{CALL return_users(?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
						
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
						
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			
			while(rs.next()){
				Reimbursement temp = new Reimbursement();
				
				temp.setId(rs.getInt("reimb_id"));
				temp.setAmount(rs.getInt("reimb_amount"));
				temp.setSubmitted(rs.getString("reimb_submitted"));
				temp.setResolved(rs.getString("reimb_resolved"));
				temp.setDescription(rs.getString("reimb_description"));
				//temp.setReceipt(rs.getBLOB("reimb_receipt"));
				temp.setAuthor(rs.getInt("reimb_author"));
				temp.setResolver(rs.getInt("reimb_resolver"));
				temp.setStatus(rs.getInt("reimb_status_id"));
				temp.setType(rs.getInt("reimb_type_id"));
				
				tickets.add(temp);
			}
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return tickets;
	}


	@Override // Pretty sure this is enough.
	public boolean deleteReimbursement(int id) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "DELETE * FROM ers_reimbursement WHERE reimb_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			conn.commit();
			
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addReimbursement(Reimbursement reimb) {
		
		Reimbursement newReimb = reimb;
		//newReimb.setSubmitted(Java.DateTime.now());
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection();){
						
			// Amount (double), description (string), author (int), type (int)
			String sql = "{call add_ticket(?, ?, ?, ?)}";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setDouble(1, reimb.getAmount());
			cstmt.setString(2, reimb.getDescription());
			cstmt.setInt(3, reimb.getAuthor());
			cstmt.setInt(4, reimb.getType());
			
			ResultSet rs = cstmt.executeQuery();
			
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateReimbursement(int id, int status) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "{call update_status(?, ?)}";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, id);
			cstmt.setInt(2, status);
			
			cstmt.executeQuery();
						
			return true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return false;
	}
}
