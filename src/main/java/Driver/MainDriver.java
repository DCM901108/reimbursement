package Driver;

<<<<<<< HEAD
import com.revature.dao.ReimbDAOImpl;
import com.revature.models.Reimbursement;
=======
import java.util.ArrayList;

import com.revature.dao.ReimbDAO;
import com.revature.dao.ReimbDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.User;
>>>>>>> 7e125cc7f1b1d745c7e5878f4ed821ea01118f1f

public class MainDriver {
	public static void main(String[] args) {
		System.out.println("hello you are in the tester");
<<<<<<< HEAD
=======
		
		ReimbDAO iu = new ReimbDAOImpl();
		ArrayList<Reimbursement> al = new ArrayList<>();
		al = iu.getReimbursementsByAuthor(3);
		System.out.println(al);
				
		
		
		
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
>>>>>>> 7e125cc7f1b1d745c7e5878f4ed821ea01118f1f

	}

}
