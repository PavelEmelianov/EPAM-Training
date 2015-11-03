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

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		String login = request.getParameter("login");
		String action = request.getParameter("action");
		
if (!"Yes".equals(action)) {
			Utils.forward("/AdminView", request, response);
			return;
		}		
		
		String address = null;
		List<String> errors = new ArrayList<>();		
		try {
			DBManager dbManager = DBManager.getInstance();
			if (dbManager.deleteUser(login)) {
				address = "/AdminView";
			} else {
				errors.add("Cannot delete user");
				address = "/ErrorPage";
			}			
		} catch (DBException ex) {
			ex.printStackTrace();
			errors.add("Cannot delete user: " + ex.getMessage());									
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);			
		}
		Utils.forward(address, request, response);
	}
	
}
