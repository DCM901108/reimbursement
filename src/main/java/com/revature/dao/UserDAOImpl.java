package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO
{

	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		System.out.println("this has been hit");
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			
			String sql = "SELECT * FROM ers_users";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				User temp = new User();
				
				temp.setErs_users_id(rs.getInt("ers_users_id"));
				temp.setErs_username(rs.getString("Ers_username"));
				temp.setErs_password(rs.getString("Ers_password"));
				temp.setUser_email(rs.getString("User_email"));
				temp.setUser_first_name(rs.getString("User_first_name"));
				temp.setUser_last_name(rs.getString("User_last_name"));
				temp.setUser_type(rs.getInt("user_role_id"));
				
			}
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User getUserById(int userId) {
		User u = new User();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ers_users where ers_users_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				u.setErs_users_id(rs.getInt("ers_users_id"));
				u.setErs_username(rs.getString("Ers_username"));
				u.setErs_password(rs.getString("Ers_password"));
				u.setUser_email(rs.getString("User_email"));
				u.setUser_first_name(rs.getString("User_first_name"));
				u.setUser_last_name(rs.getString("User_last_name"));
				u.setUser_type(rs.getInt("user_role_id"));
			}
		
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		User u = new User();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();)
		{
			String sql = "SELECT * FROM ers_users WHERE ers_username = ? and ers_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next())
			{
				u.setErs_users_id(rs.getInt("ers_users_id"));
				u.setErs_username(rs.getString("Ers_username"));
				u.setErs_password(rs.getString("Ers_password"));
				u.setUser_email(rs.getString("User_email"));
				u.setUser_first_name(rs.getString("User_first_name"));
				u.setUser_last_name(rs.getString("User_last_name"));
				u.setUser_type(rs.getInt("user_role_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User addUser(User newUser) {
		User u = new User();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();)
		{
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ers_users (ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
			String[] values = new String[1];
			values[0]= "ers_users_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, values);
			pstmt.setString(1, newUser.getErs_username());
			pstmt.setString(1, newUser.getErs_password());
			pstmt.setString(2, newUser.getUser_first_name());
			pstmt.setString(3, newUser.getUser_last_name());
			pstmt.setString(4, newUser.getUser_email());
			pstmt.setInt(5, newUser.getUser_type());
			
			int rowsInserted = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			if(rowsInserted != 0)
			{
				while(rs.next()) 
				{
					u.setErs_users_id(rs.getInt(1));
					conn.commit();
				}
				
				u.setErs_password(newUser.getErs_password());
				u.setErs_username(newUser.getErs_username());
				u.setUser_email(newUser.getUser_email());
				u.setUser_first_name(newUser.getUser_first_name());
				u.setUser_last_name(newUser.getUser_last_name());
				u.setUser_type(newUser.getUser_type());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User getUserByUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
