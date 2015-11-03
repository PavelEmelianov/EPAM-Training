package ua.nure.emelianov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command for registration page from login page
 * @author Emelianov Pavel
 *
 */
public class RegistrationPageCommand extends Command {

	private static final long serialVersionUID = 6332327043897077039L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		return Path.REGISTRATION_PAGE;
	}

}
