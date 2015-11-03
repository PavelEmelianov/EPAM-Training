package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command blocks user
 * @author Emelianov Pavel
 *
 */
public class BlockUserCommand extends Command {

	private static final long serialVersionUID = 3685196272345375594L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String page = Path.ERROR_PAGE;
		String login = request.getParameter("login");
		HttpSession session = request.getSession(false);
		DBManager db = DBManager.getInstance();
		boolean status = db.updateUserStatusByLogin(login);
		request.setAttribute("login", login);
		request.setAttribute("status", status);
		List<Users> list = db.getUsers();
		session.setAttribute("usersList", list);
		page = Path.ACTION_PAGE;
		return page;

	}
}
