package ua.nure.your_last_name.Practice9.web.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.Utils;
import ua.nure.your_last_name.Practice9.db.DBException;
import ua.nure.your_last_name.Practice9.db.DBManager;
import ua.nure.your_last_name.Practice9.db.entity.Role;
import ua.nure.your_last_name.Practice9.db.entity.User;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "/ErrorPage";
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFullName(fullName);
		user.setEmail(email);
		
		List<String> errors = new ArrayList<>();		
		if (!Utils.validateSize(login, 5, "Login", errors)
				| !Utils.validateSize(password, 3, "Password", errors)
				| !Utils.validateSize(fullName, 0, "Full Name", errors)
				| !Utils.validatePattern(email, ".*@.*", "Email", errors)
				) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", user);
			Utils.forward("/AddUserView", request, response);
			return;
		}		
		
		String roleName = request.getParameter("role");
		if (roleName == null || roleName.isEmpty()) {
			errors.add("Role cannot be empty");
		}		
		
		try {
			DBManager dbManager = DBManager.getInstance();
			Role role = dbManager.findRoleByName(roleName);
			user.setRoleId(role.getId());
			if (errors.isEmpty() && dbManager.insertUser(user)) {
				address = "/AdminView";
			} else {
				errors.add("Cannot add new user");
			}
		} catch (DBException | NullPointerException ex) {
			ex.printStackTrace();
			errors.add("Cannot add new user: " + ex.getMessage());									
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);			
		}
		Utils.forward(address, request, response);
	}

}
