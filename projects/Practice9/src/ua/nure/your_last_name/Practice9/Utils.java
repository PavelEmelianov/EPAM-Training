package ua.nure.your_last_name.Practice9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.db.entity.Role;
import ua.nure.your_last_name.Practice9.db.entity.User;

public class Utils {

	private Utils() {
		// no op
	}

	public static final void printLoggedAs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		User u = (User) request.getSession().getAttribute("currentUser");
		Role r = (Role) request.getSession().getAttribute("currentRole");
		out.printf("<p align='right'>You are logged as %s %s, <a href='Logout'>logout</a></p>", r.getName(), u.getFullName());
				
	}

	public static final void printGoBackLink(HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<p><a href='javascript:history.back()'>Go Back</a></p>");
	}

	public static void printHead(HttpServletResponse response, String title)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.printf("<title>%s</title>", title);
		out.println("<meta http-equiv='Content-Type' content='test/html; charset=UTF-8'>");
		out.println("</head>");
	}

	public static void printWarning(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<h3 style='color: red'>Данный проект дан в качестве примера. Сервлеты не должны содержать HTML разметки.<br/>");
		out.println("Для формирования представления используйте JSP (см. проект Practice9Jsp)</h3>");
	}

	public static boolean validateSize(String input, int size, String name,	List<String> errors) {
		if (size == 0 && (input == null || input.length() == 0)) {
			errors.add(name + " must not be empty.");
			return false;
		}
		if (size > 0 && (input == null || input.length() < size)) {
			errors.add(String.format("%s must contain %d or more characters.", name, size));
			return false;
		}
		return true;
	}

	public static boolean validatePattern(String input, String regexp, String name, List<String> errors) {
		if (!Pattern.matches(regexp, input)) {
			errors.add(String.format("%s must match regexp: %s", name, regexp));
			return false;
		}		
		return true;
	}
	
	public static void forward(String location, ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(location).forward(request, response);
	}

}
