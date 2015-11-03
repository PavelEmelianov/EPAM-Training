package ua.nure.your_last_name.Practice9.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calc")
public class Calc extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// obtain parameter values
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		String op = request.getParameter("op");
		
		// obtain result
		int res = 0;
		switch (op) {
		case "+":
			res = x + y;
			break;
		case "-":
			res = x - y;
			break;
		}
		
		// print result
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>Result</title></head>");
		out.println("<body>");

		out.printf("%d %s %d = %d", x, op, y, res);
		out.printf("<hr/>");
		out.printf("<a href='calc.html'>back</a>");

		out.println("</body></html>");
	}

}
