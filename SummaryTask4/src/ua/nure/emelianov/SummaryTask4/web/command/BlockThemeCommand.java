package ua.nure.emelianov.SummaryTask4.web.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.exception.AppException;


 /**
 * command blocks theme (blocked theme is invisible for student)
 * @author Emelianov Pavel
 *
 */

public class BlockThemeCommand extends Command {

	private static final long serialVersionUID = 6079847757994770011L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String page = Path.ERROR_PAGE;
		
		HttpSession session = request.getSession(false);
		
		DBManager db = DBManager.getInstance();
		
		int themeId =Integer.valueOf(request.getParameter("themeId"));
		
		boolean status = db.updateThemeStatusById(themeId);
		
		request.setAttribute("status", status);
		
		session.setAttribute("themesList", db.getThemes());
		page = Path.ADMIN_PAGE;
		return page;
	}

}
