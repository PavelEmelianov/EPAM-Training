package ua.nure.your_last_name.Practice9.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.your_last_name.Practice9.Utils;
import ua.nure.your_last_name.Practice9.db.entity.Role;

public class SecurityFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String path = httpRequest.getServletPath();
		System.out.println("servlet path ==> " + path);

		// allow access to calculator :)
		switch (path) {
		case "/calc.html":
		case "/Calc":		
			chain.doFilter(request, response);
			return;
		}

		// allow access to common resources and resources not under control
		switch (path) {
		case "/":
		case "/IndexView":
		case "/Login":
		case "/Logout":
		case "/ErrorPage":
			chain.doFilter(request, response);
			return;
		}

		// check session
		HttpSession session = httpRequest.getSession();
		if (session == null) {
			request.setAttribute("errors", Arrays.asList("Access denied"));
			Utils.forward("/ErrorPage", request, response);
			return;
		} else {
			Role currentRole = (Role) session.getAttribute("currentRole");			
			if (currentRole != null) {
				// for clients
				if (Role.ROLE_CLIENT.equals(currentRole.getName())) {
					switch (path) {
					case "/EditUser":
					case "/EditUserView":
					case "/ClientView":
						chain.doFilter(request, response);
						return;
					}
				}
				
				// for administrators
				if (Role.ROLE_ADMIN.equals(currentRole.getName())) {
					switch (path) {
					case "/EditUser":
					case "/EditUserView":
					case "/AdminView":
					case "/AdminUserView":
					case "/AddUserView":
					case "/AddUser":
					case "/DeleteUser":
					case "/ConfirmDeleteUserView":
						chain.doFilter(request, response);
						return;
					}
				}

				List<String> errors = new ArrayList<>();
				errors.add("A resource not under control: " + path);
				errors.add("Adjust Security Filter to allow access");
				request.setAttribute("errors", errors);
				Utils.forward("/ErrorPage", request, response);
				return;
			}
		}
		request.setAttribute("message", "Session timeout");
		Utils.forward("/IndexView", httpRequest, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// no op
	}

	public void destroy() {
		// no op
	}

}
