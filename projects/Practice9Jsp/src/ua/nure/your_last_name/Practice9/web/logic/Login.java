package ua.nure.your_last_name.Practice9.web.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.your_last_name.Practice9.Utils;
import ua.nure.your_last_name.Practice9.db.DBException;
import ua.nure.your_last_name.Practice9.db.DBManager;
import ua.nure.your_last_name.Practice9.db.entity.Role;
import ua.nure.your_last_name.Practice9.db.entity.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 2531529377915951889L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = null;
		
		// obtain parameters
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		
		List<String> errors = new ArrayList<>();
		if (login == null || login.isEmpty()) {
			errors.add("Login cannot be empty");			
		}
		if (pass == null || pass.isEmpty()) {
			errors.add("Password cannot be empty");			
		}
		
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
			Utils.forward("ErrorPage", request, response);
			return;
		}
		
		try {
			DBManager dbManager = DBManager.getInstance();
			User user = dbManager.findUser(login);
			if (user != null) {				
				if (user.getPassword().equals(pass)) {
					// all ok
					HttpSession session = request.getSession();
					session.setAttribute("currentUser", user);
					
					Role role = dbManager.findRoleByUser(user);
					session.setAttribute("currentRole", role);
					
					// obtain role
					switch (role.getName()) {
					case Role.ROLE_ADMIN:						
						address = "/ListUsers";
						break;
					case Role.ROLE_CLIENT:
						address = "/ListClients";
						break;
					default:
						errors.add("Unknown role id for user: " + user.getFullName());						
					} 
				} else {
					errors.add("Password incorrect for " + user.getLogin());					
				}
			} else {
				errors.add("No user with such login: " + login);
			}
		} catch (DBException | NullPointerException ex) {
			ex.printStackTrace();
			errors.add("Cannot authenticate user: " + ex.getMessage());									
		}		
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
			Utils.forward("ErrorPage", request, response);
		} else {
			Utils.forward(address, request, response);
		}
	}	

}