package ua.nure.your_last_name.Practice9.web.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.Utils;
import ua.nure.your_last_name.Practice9.db.entity.User;

@WebServlet("/AddUserView")
public class AddUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);		
	}
	
	protected void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html>");		
		Utils.printHead(response, "Add User");		
		out.println("<body>");
		Utils.printWarning(response);
		Utils.printLoggedAs(request, response);
		
		@SuppressWarnings("unchecked")
		List<String> errors = (List<String>)request.getAttribute("errors");		
		if (errors != null && errors.size() > 0) {
			out.println("<p style='color: red'>");
			for (String message : errors) {
				out.printf("%s</br>", message);
			}
			out.println("<p>");
		}
		
		User user = (User)request.getAttribute("user");
		
		out.println("<form action='AddUser' method='post'>");
		
		
		out.println("<table>");
		
		out.println("<tr>");		
		out.println("<td>Login</td>");		
		out.printf("<td><input name='login' %s></td>", user != null ? "value='" + user.getLogin() + "'" : "");
		out.println("</tr>");		
		
		out.println("<tr>");		
		out.println("<td>Password</td>");
		out.printf("<td><input name='password' %s></td>", user != null ? "value='" + user.getPassword() + "'" : "");
		out.println("</tr>");

		out.println("<tr>");		
		out.println("<td>Full Name</td>");
		out.printf("<td><input name='fullName' %s></td>", user != null ? "value='" + user.getFullName() + "'" : "");
		
		out.println("</tr>");

		out.println("<tr>");		
		out.println("<td>Email</td>");
		out.printf("<td><input name='email' %s></td>", user != null ? "value='" + user.getEmail() + "'" : "");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>Role</td>");
		out.println("<td>");
		out.println("<select name='role'>");
		out.println("<option value='admin'>admin</option>");
		out.println("<option value='client'>client</option>");
		out.println("</select>");		
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");		
		out.println("<td></td>");
		out.println("<td><input type='submit' value='Add new user'></td>");
		out.println("</tr>");

		
		out.println("</table>");
		out.println("</form>");
		
		Utils.printGoBackLink(response);
		
		out.println("</body></html>");
	}

}
