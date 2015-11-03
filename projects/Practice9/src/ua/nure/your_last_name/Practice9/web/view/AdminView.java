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
import ua.nure.your_last_name.Practice9.db.bean.UserBean;

/**
 * Servlet implementation class Client
 */
@WebServlet("/AdminView")
public class AdminView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
       
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html>");		
		Utils.printHead(response, "List Users");		
		out.println("<body>");
		Utils.printWarning(response);
		Utils.printLoggedAs(request, response);
		
		try {
			DBManager dbManager = DBManager.getInstance();			
			out.println("<a href='AddUserView' align='left'>add</a>");
			
			List<UserBean> beans = dbManager.findAllUserBeans();
			out.println("<table border='1'>");
			
			out.println("<tr>");
			
			out.println("<th>Login</th>");
			out.println("<th>Full Name</th>");
			out.println("<th>Email</th>");
			out.println("<th>Role</th>");
			out.println("<th></th>");
			out.println("<th></th>");				
		
			out.println("</tr>");
			
			
			for (UserBean bean : beans) {
				out.println("<tr>");
				
				out.printf("<td>%s</td>", bean.getLogin());
				out.printf("<td>%s</td>", bean.getFullName());
				out.printf("<td>%s</td>", bean.getEmail());
				out.printf("<td>%s</td>", bean.getRoleName());
				out.printf("<td><a href='ConfirmDeleteUserView?login=%s'>delete</a></td>", bean.getLogin());
				out.printf("<td><a href='EditUserView?login=%s'>edit</a></td>", bean.getLogin());				
			
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
