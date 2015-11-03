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

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "ErrorPage";
		List<String> errors = new ArrayList<>();

		String login = request.getParameter("login");

		try {
			DBManager dbManager = DBManager.getInstance();
			User user = dbManager.findUser(login);

			String password = request.getParameter("password");			
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			
			if (!Utils.validateSize(password, 3, "Password", errors)
					| !Utils.validateSize(fullName, 0, "Full Name", errors)
					| !Utils.validatePattern(email, ".*@.*", "Email", errors)
					) {
				request.setAttribute("errors", errors);
				Utils.forward("/EditUserView", request, response);
				return;
			}
			
			user.setPassword(password);
			user.setFullName(fullName);
			user.setEmail(email);

			if (dbManager.updateUser(user)) {
				Role currentRole = (Role) request.getSession().getAttribute("currentRole");
				String currentRoleName = currentRole.getName();
				switch (currentRoleName) {
				case Role.ROLE_ADMIN:
					address = "/AdminView";
					break;
				case Role.ROLE_CLIENT:
					address = "/ClientView";
				}
			} else {
				errors.add("Cannot update user");
				address = "ErrorPage";
			}
		} catch (DBException | NullPointerException ex) {
			ex.printStackTrace();
			errors.add("Cannot update user: " + ex.getMessage());
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
		}		
		Utils.forward(address, request, response);
	}

}
