package ua.nure.your_last_name.Practice9.web.logic.client;

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
import ua.nure.your_last_name.Practice9.db.entity.User;

@WebServlet("/EditClient")
public class EditClient extends HttpServlet {
	
	private static final long serialVersionUID = -4818784278068563348L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "ErrorPage";
		List<String> errors = new ArrayList<>();
		String login = request.getParameter("login");
		try {
			DBManager dbManager = DBManager.getInstance();
			User user = dbManager.findUser(login);
			request.getSession().setAttribute("userToEdit", user);
			address = "EditClientView";
		} catch (DBException ex) {
			ex.printStackTrace();
			errors.add("Cannot edit client: " + ex.getMessage());
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
		}		
		Utils.forward(address, request, response);
	}

}
