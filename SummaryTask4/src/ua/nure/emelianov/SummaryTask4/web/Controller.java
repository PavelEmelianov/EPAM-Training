package ua.nure.emelianov.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
import ua.nure.emelianov.SummaryTask4.web.command.Command;
import ua.nure.emelianov.SummaryTask4.web.command.CommandContainer;

/**
 * Servlet
 * @author Emelianov Pavel
 *
 */

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("DoGet");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("DoPost");
		process(request, response);

	}

	/**
	 * method gets command from command container
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String forward = Path.ERROR_PAGE;

		String commandName = request.getParameter("command");

		if (!commandName.equals("login")
				&& request.getSession().getAttribute("user") == null
				&& !commandName.equals("RegistrationPage")
				&& !commandName.equals("registration")) {
			request.getRequestDispatcher(Path.LOGIN_PAGE).forward(request,
					response);
			request.getSession().invalidate();
			return;
		}

		Command command = CommandContainer.get(commandName);
		
		LOG.debug("command: " + forward);

		try {
			forward=command.execute(request, response);
		} catch (AppException e) {
			if (e.getCause() != null) {
				request.setAttribute("errorMessage", e.getMessage() + ": "
						+ e.getCause().getMessage());
			} else {
				request.setAttribute("errorMessage", e.getMessage());
			}
		}

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
