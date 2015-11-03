package ua.nure.emelianov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * logout command, invalidates session
 * @author Emelianov Pavel
 *
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -326053996080792166L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return Path.LOGIN_PAGE;
	}

}
