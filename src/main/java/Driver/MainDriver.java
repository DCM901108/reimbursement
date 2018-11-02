package Driver;

import java.util.ArrayList;

import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

public class MainDriver {
	public static void main(String[] args) {
		UserDAOImpl userDAO = new UserDAOImpl();
		//ArrayList<User> users = 
		//System.out.println(userDAO.getUserByCredentials("test","test").toString());
		/*
		for(User u : users) {
			System.out.println(u.toString());
		}/**/
	}

}
