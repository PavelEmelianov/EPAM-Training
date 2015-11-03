package ua.nure.your_last_name.Practice9.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.Utils;

@WebServlet("/ErrorPage")
public class ErrorPage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		Utils.printWarning(response);
		Utils.printHead(response, "Error");		
		out.println("<body>");		
		
		out.println("<h3 style='color: red'>Error</h3>");
		
		// if http error status
		Object status = request.getAttribute("javax.servlet.error.message");
		if (status != null) {
			out.println(request.getAttribute("javax.servlet.error.message"));
		}
	
		// if error messages present
		@SuppressWarnings("unchecked")
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String message : errors) {
				out.println(message + "<br/>");
			}
		}
		
		out.println("<p><a href='javascript:history.back()'>Go Back</a></p>");
		out.println("</body></html>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);	
	}

	
}
