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
	public List<Reimbursement> getAllReimbursements(){
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "SELECT * FROM ers_reimbursement";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
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
			
			return tickets;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthor(int id) {
		
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "SELECT * FROM ers_reimbursment WHERE reimb_author = ?";
			
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
				//temp.setReceipt(rs.getBLOB("reimb_receipt"));
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
	public List<Reimbursement> getReimbursementsByStatus(int status) {
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

	@Override
	public boolean updateReimbursement(int id) {
		// TODO Add update functionality here
		return false;
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
	public Reimbursement addReimbursement(Reimbursement reimb) {
		
		Reimbursement newReimb = reimb;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection();){
						
			// Amount (double), date (string), description (string), author (int), type (int)
			String sql = "{call add_ticket(?, ?, ?, ?, ?)}";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setDouble(1, reimb.getAmount());
			cstmt.setString(2, reimb.getSubmitted()); // Must be in 'yyyy:mm:dd hh:mi:ss' format.
			cstmt.setString(3, reimb.getDescription());
			cstmt.setInt(4, reimb.getAuthor());
			cstmt.setInt(5, reimb.getType());
			
			ResultSet rs = cstmt.executeQuery();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return newReimb;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		
		Reimbursement newReimb = new Reimbursement();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "SELECT * from ers_reimbursement WHERE reimb_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				newReimb = (Reimbursement) rs;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return newReimb;
	}
}
