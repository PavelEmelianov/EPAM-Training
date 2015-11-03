package ua.nure.emelianov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command goes to homepage depending on users role
 * @author Emelianov Pavel
 *
 */
public class HomePageCommand extends Command {

	private static final long serialVersionUID = -839871637717116969L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.ERROR_PAGE;

		HttpSession session = request.getSession(false);

		Users user = (Users) session.getAttribute("user");
		if (user.getRoleId() == 0) {
			page = Path.ADMIN_PAGE;
			return page;
		}
		if (user.getRoleId() == 1) {
			page = Path.CLIENT_PAGE;
			return page;
		}

		return page;
	}

}
