package ua.nure.your_last_name.Practice9.web.logic.admin;

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

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {

	private static final long serialVersionUID = -128868774523774914L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "/ErrorPage";
		List<String> errors = new ArrayList<>();
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("userToAdd");
		if (user == null) {
			user = new User();
			session.setAttribute("userToAdd", user);
		}
		
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setFullName(request.getParameter("fullName"));
		user.setEmail(request.getParameter("email"));	
		
		String roleName = request.getParameter("roleName");
		if (roleName == null || roleName.isEmpty()) {
			errors.add("Role cannot be empty");
		} else {
			session.setAttribute("roleName", roleName);
		}
				
		if (!Utils.validateSize(user.getLogin(), 5, "Login", errors)
				| !Utils.validateSize(user.getPassword(), 3, "Password", errors)
				| !Utils.validateSize(user.getFullName(), 0, "Full Name", errors)
				| !Utils.validatePattern(user.getEmail(), ".*@.*", "Email", errors)
				) {
			request.setAttribute("errors", errors);
			Utils.forward("/AddUserView", request, response);
			return;
		}		
		
		try {
			DBManager dbManager = DBManager.getInstance();
			Role role = dbManager.findRoleByName(roleName);
			user.setRoleId(role.getId());
			if (errors.isEmpty() && dbManager.insertUser(user)) {
				address = "/ListUsers";
			} else {
				errors.add("Cannot add new user");
			}
		} catch (DBException | NullPointerException ex) {
			ex.printStackTrace();
			errors.add("Cannot add new user: " + ex.getMessage());									
		} finally {
			session.removeAttribute("userToAdd");
			session.removeAttribute("roleName");
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);			
		}
		Utils.forward(address, request, response);
	}

}