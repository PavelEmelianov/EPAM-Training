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
 * command gets users
 * @author Emelianov Pavel
 *
 */
public class GetUsersCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		
		if (user.getRoleId() == 1) {
			throw new AppException("access denied");
		}

		DBManager db = DBManager.getInstance();
		List<Users> list = db.getUsers();
		session.setAttribute("usersList", list);
		return Path.GET_USERS;
	}
}
