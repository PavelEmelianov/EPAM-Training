package ua.nure.emelianov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command saves values for further refactoring
 * @author Emelianov Pavel
 *
 */
public class RefactorPageCommand extends Command {

	private static final long serialVersionUID = 5506972675063509245L;

	private static  final String ANSWER_ID="answerId";
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		
		String page = Path.ERROR_PAGE;
		
		HttpSession session = request.getSession(false);
		
		Users user = (Users) session.getAttribute("user");
		
		if (user.getRoleId() == 1) {
			throw new AppException("access denied");
		}
		
		String name = request.getParameter("name");
		
		if("answer".equals(name)){
			
			String answerId=request.getParameter(ANSWER_ID);
			session.setAttribute(ANSWER_ID, answerId);
			
		} else if("deleteTheme".equals(name)){
			String themeId = request.getParameter("theme");
			session.setAttribute("themeId", themeId);
		}else if("deleteTest".equals(name)){
			String testId = request.getParameter("testId");
			session.setAttribute("testId", testId);
		}else if("deleteQuestion".equals(name)){
			String questionId = request.getParameter("questionId");
			session.setAttribute("questionId", questionId);
		}
		else if("deleteAnswer".equals(name)){
			String answerId = request.getParameter(ANSWER_ID);
			session.setAttribute(ANSWER_ID, answerId);
		}
		
		
		session.setAttribute("name", name);
		
		page=Path.CHANGE_NAME_PAGE;
		return page;
	}

}
