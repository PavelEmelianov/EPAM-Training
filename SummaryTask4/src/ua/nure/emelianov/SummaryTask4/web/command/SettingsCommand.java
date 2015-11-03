package ua.nure.emelianov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ua.nure.emelianov.SummaryTask4.Path;

import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command for settings page where change language form located
 * @author Emelianov Pavel
 *
 */
public class SettingsCommand extends Command {

	private static final long serialVersionUID = -6324558565761830645L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.SETTINGS_PAGE;
		
		return page;
	}

}
