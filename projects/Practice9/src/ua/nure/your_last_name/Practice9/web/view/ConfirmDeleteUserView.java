package ua.nure.your_last_name.Practice9.web.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.Utils;

@WebServlet("/ConfirmDeleteUserView")
public class ConfirmDeleteUserView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);		
	}
	
	protected void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");
		
		out.println("<html>");		
		Utils.printHead(response, "Confirm Delete User");		
		out.println("<body>");
		Utils.printWarning(response);
		Utils.printLoggedAs(request, response);
		
		out.println("<form action='DeleteUser' method='post'>");
		
		out.printf("<input type='hidden' name='login' value='%s'", login);
		
		out.printf("<p>Are you shure to delete user with login %s?</p>", login);
		out.println("<input name='action' type='submit' value='Yes'>");
		out.println("<input name='action' type='submit' value='No'>");
		
		out.println("</form>");
		
		Utils.printGoBackLink(response);
		
		out.println("</body></html>");
	}

}
