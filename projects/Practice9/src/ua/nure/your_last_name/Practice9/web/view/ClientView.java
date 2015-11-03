package ua.nure.your_last_name.Practice9.web.view;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/ClientView")
public class ClientView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html>");		
		Utils.printHead(response, "Clients");		
		out.println("<body>");
		Utils.printWarning(response);
		Utils.printLoggedAs(request, response);
		
		try {
			DBManager dbManager = DBManager.getInstance();			
			
			List<User> clients = dbManager.findUsersByRoleName("client");
			out.println("<table border='1'>");
			
			out.println("<tr>");
			out.println("<th>Full Name</th>");
			out.println("<th>Email</th>");
			out.println("<th></th>");
			out.println("</tr>");
			
			for (User client : clients) {
				out.println("<tr>");
				
				out.printf("<td>%s</td>", client.getFullName());
				out.printf("<td>%s</td>", client.getEmail());

				User currentUser = (User) request.getSession().getAttribute("currentUser");
				if (currentUser.getLogin().equals(client.getLogin())) {
					out.printf("<td><a href='EditUserView?login=%s'>edit</a></td>", client.getLogin());	
				} else {
					out.println("<td></td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (DBException ex) {
			ex.printStackTrace();
			request.setAttribute("errors", Arrays.asList("Cannot list clients: " + ex.getMessage()));
			Utils.forward("ErrorPage", request, response);
		}
	}

}
