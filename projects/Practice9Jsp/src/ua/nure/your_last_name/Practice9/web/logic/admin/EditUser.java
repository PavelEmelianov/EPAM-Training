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

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	
	private static final long serialVersionUID = -4818784278068563348L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "ErrorPage";
		List<String> errors = new ArrayList<>();
		HttpSession session = request.getSession();

		String login = request.getParameter("login");

		try {
			DBManager dbManager = DBManager.getInstance();
			User user = dbManager.findUser(login);			
			Role role = dbManager.findRoleByUser(user);
			session.setAttribute("userToEdit", user);
			session.setAttribute("roleName", role.getName());
			address = "EditUserView";
		} catch (DBException ex) {
			ex.printStackTrace();
			errors.add("Cannot edit a user: " + ex.getMessage());
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
		}		
		Utils.forward(address, request, response);
	}

}
