package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Answers;
import ua.nure.emelianov.SummaryTask4.exception.AppException;

/**
 * command updates answer status
 * @author Emelianov Pavel
 *
 */
public class AnswerStatusCommand extends Command {

	private static final long serialVersionUID = -7914703812630254769L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		
		String page = Path.ERROR_PAGE;
		
		HttpSession session = request.getSession(false);
		DBManager db = DBManager.getInstance();
		String id = request.getParameter("answerId");
		db.updateAnswerStatusbyId(id);
		List<Answers> list =db.findAnswersByQuestionId((Integer)session.getAttribute("questionId"));
		session.setAttribute("answersList", list);
		page = Path.EDIT_QUESTION_PAGE;
		return page;
	}

}
