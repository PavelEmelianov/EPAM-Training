package ua.nure.your_last_name.Practice9.web.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.Utils;

@WebServlet("/IndexView")
public class IndexView extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<html>");
		Utils.printHead(response, "Authorization");
		out.println("<body>");		
		Utils.printWarning(response);		

		String message = (String)request.getAttribute("message");
		if (message != null && !message.isEmpty()) {
			out.printf("<p style='color: red'>%s<p>", message);	
		}		
		
		out.println("<form action='Login' method='post'>");
		out.println("<table>");
		
		out.println("<tr>");
		out.println("<td>Login</td>");
		out.println("<td><input name='login'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>Password</td>");
		out.println("<td><input name='password'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td></td>");
		out.println("<td><input type='submit' value='Authenticate'></td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		
		out.println("</body></html>");
	}

}
