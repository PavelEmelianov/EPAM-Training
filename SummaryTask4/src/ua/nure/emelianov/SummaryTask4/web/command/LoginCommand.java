package ua.nure.emelianov.SummaryTask4.web.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.emelianov.SummaryTask.security.Password;
import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.customTag.FormatToMin;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * login command, creates new session
 * @author Emelianov Pavel
 *
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = 6653889182120230875L;

	private static final Logger LOG = Logger.getLogger(FormatToMin.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		HttpSession session = request.getSession();
		
		

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		LOG.debug("Login: "+ login);
		LOG.debug("Password: "+ password);

		if (login == null || password == null || login.isEmpty()
				|| password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
		
		DBManager db = DBManager.getInstance();
		Users user = db.findUserByLogin(login);
		if (user == null || !Password.hash(password).equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}
		
		int userId=user.getId();
		

		String page = Path.ERROR_PAGE;

		if (user.getRoleId() == 0) {
			page = Path.ADMIN_PAGE;
		} else {
			if (user.getBlocked()) {
				throw new AppException("user is blocked");
			}
			page = Path.CLIENT_PAGE;
		}

		session.setAttribute("themesList", db.getThemes());
		session.setAttribute("user", user);
		session.setAttribute("userId",userId);

		return page;
	}
}
