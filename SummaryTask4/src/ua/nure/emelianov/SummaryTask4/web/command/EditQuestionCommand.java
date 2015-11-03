package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Answers;

import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * edit question command
 * @author Emelianov Pavel
 *
 */
public class EditQuestionCommand extends Command {
	
	private static final String QUESTION_NAME="questionName";
	private static final String QUESTION_ID="questionId";

	private static final long serialVersionUID = 3383061786176200863L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.ERROR_PAGE;

		HttpSession session = request.getSession(false);
		DBManager db = DBManager.getInstance();

		Users user = (Users) session.getAttribute("user");

		if (user.getRoleId() == 1) {
			throw new AppException("access denied");
		}
		
		String questionName;
		
		if(request.getParameter(QUESTION_NAME)!=null){
		questionName = request.getParameter(QUESTION_NAME);}
		else{
			questionName=(String)session.getAttribute(QUESTION_NAME);
		}

		

		int questionId;
		
		if(request.getParameter(QUESTION_ID)!=null){
		questionId = Integer.valueOf(request.getParameter(QUESTION_ID));
		} else{
			questionId=(Integer.valueOf((String.valueOf(session.getAttribute(QUESTION_ID)))));
		}
		List<Answers> answers = db.findAnswersByQuestionId(questionId);

		session.setAttribute("questionId", questionId);
		session.setAttribute("answersList", answers);
		session.setAttribute("questionName", questionName);

		page = Path.EDIT_QUESTION_PAGE;

		return page;
	}

}
