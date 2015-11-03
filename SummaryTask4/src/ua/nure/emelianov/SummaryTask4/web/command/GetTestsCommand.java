package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.*;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Tests;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command gets tests by theme id
 * @author Emelianov Pavel
 *
 */
public class GetTestsCommand extends Command {

	private static final long serialVersionUID = -3724868789969200395L;
	
	private static final String THEME = "theme";
	private static final String THEME_NAME = "themeName";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.ERROR_PAGE;

		DBManager db = DBManager.getInstance();
		HttpSession session = request.getSession(false);

		int theme = -1;

		if (session.getAttribute(THEME) != null) {
			theme = (Integer) session.getAttribute(THEME);
		}

		if (request.getParameter(THEME) != null) {
			theme = Integer.valueOf(request.getParameter(THEME));
		}

		String sort = request.getParameter("sort");

		List<Tests> tests = db.findTestsByTheme(theme);

		if ("name".equals(sort)) {
			Collections.sort(tests, new Comparators.CompareByName());
		} else if ("diff".equals(sort)) {
			Collections.sort(tests, new Comparators.CompareByDifficulty());
		} else if ("qcount".equals(sort)) {
			Collections.sort(tests, new Comparators.CompareByQuestionsCount());

		}
		
		String themeName;
		
		if(request.getParameter(THEME_NAME)!=null){
			themeName=request.getParameter(THEME_NAME);
		}else{
			themeName= (String)session.getAttribute(THEME_NAME);
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getRoleId() == 1&&tests.size()==0) {

				throw new AppException("No tests in this theme");
			
		}
		session.setAttribute(THEME_NAME, themeName);
		session.setAttribute("tests", tests);
		session.setAttribute(THEME, theme);

		page = Path.TESTS_PAGE;
		return page;

	}
}
