package ua.nure.your_last_name.Practice9.web.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * Servlet implementation class EditUserView
 */
@WebServlet("/EditUserView")
public class EditUserView extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		PrintWriter out = response.getWriter();

		String login = request.getParameter("login");

		User user = null;
		try {
			DBManager dbManager = DBManager.getInstance();
			user = dbManager.findUser(login);
			if (user == null) {
				errors.add("Cannot edit a user");
			}
		} catch (DBException ex) {
			errors.add("Cannot edit a user: " + ex.getMessage());			
		}
		
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
			Utils.forward("/ErrorPage", request, response);
			return;
		}

		out.println("<html>");
		Utils.printHead(response, "Edit User");
		out.println("<body>");
		Utils.printWarning(response);
		Utils.printLoggedAs(request, response);
		
		errors = (List<String>)request.getAttribute("errors");		
		if (errors != null && errors.size() > 0) {
			out.println("<p style='color: red'>");
			for (String message : errors) {
				out.printf("%s</br>", message);
			}
			out.println("<p>");
		}

		out.println("<form action='EditUser' method='post'>");
		out.printf("<input type='hidden' name='login' value='%s'>", login);

		out.println("<table>");

		out.println("<tr>");
		out.println("<td>Password</td>");
		out.printf("<td><input name='password' value='%s'></td>",
				user.getPassword());
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Full Name</td>");
		out.printf("<td><input name='fullName' value='%s'></td>",
				user.getFullName());
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Email</td>");
		out.printf("<td><input name='email' value='%s'></td>", user.getEmail());
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td></td>");
		out.println("<td><input type='submit' value='Update user'></td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("</form>");

		Utils.printGoBackLink(response);

		out.println("</body></html>");
	}

}
