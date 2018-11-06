package Driver;

import com.revature.dao.ReimbDAOImpl;
import com.revature.models.Reimbursement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.dao.ReimbDAO;
import com.revature.dao.ReimbDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class MainDriver {
	public static void main(String[] args) {
		System.out.println("hello you are in the tester");
		
//		ReimbDAO iu = new ReimbDAOImpl();
//		ArrayList<Reimbursement> al = new ArrayList<>();
//		al = iu.getReimbursementsByAuthor(3);
//		System.out.println(al);
				
//
//		public boolean updateReimbursement(int id, int resolver, int status) {
			
		

		ReimbDAOImpl reimbDAO = new ReimbDAOImpl();
		Reimbursement newReimb = new Reimbursement();
//		
		reimbDAO.updateReimbursement(29, 6, 3);
		
		// Amount (double), description (string), author (int), type (int)
//		newReimb.setAmount(57.86);
//		newReimb.setDescription("Motel 6");
//		newReimb.setAuthor(3);
//		newReimb.setType(1);
		
//		reimbDAO.addReimbursement(newReimb);
		
//		UserDAO iu = new UserDAOImpl();
//		User newUser = new User();
//		
//		newUser.setErs_password("DAOTEST5523455");
//		newUser.setErs_username("DAOTEST2345555");
//		newUser.setUser_email("DAOTEST5234555");
//		newUser.setUser_first_name("DAOTEST5555234");
//		newUser.setUser_last_name("DAOTEST42235554223434344");
//		newUser.setUser_type(1);
//		
//		newUser=iu.addUser(newUser);
	}

}
