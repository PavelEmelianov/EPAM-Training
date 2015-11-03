package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Questions;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * edit test command
 * @author Emelianov Pavel
 *
 */
public class EditTestCommand extends Command {
	
	private static final String TEST_TIME ="testTime";
	private static final String TEST_DIFFICULTY="testDifficulty" ;
	private static final String TEST_ID  ="testId";
	private static final String TEST_NAME ="testName";

	private static final long serialVersionUID = -4856131614277911987L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		
		
		HttpSession session = request.getSession(false);
		
		Users user = (Users) session.getAttribute("user");
		
		if (user.getRoleId() == 1) {
			throw new AppException("access denied");
		}

		String page = Path.ERROR_PAGE;
		DBManager db = DBManager.getInstance();

		
		int testId;
		if (request.getParameter(TEST_ID) != null) {
			testId = Integer.valueOf(request.getParameter(TEST_ID));
		}else{
			testId=(Integer)session.getAttribute(TEST_ID);
		}
		String testName;
		String testDifficulty;
		String testTime;

		if (request.getParameter(TEST_NAME) != null) {
			testName = request.getParameter(TEST_NAME);
		} else {
			testName = (String) session.getAttribute(TEST_NAME);
		}

		if (request.getParameter(TEST_DIFFICULTY) != null) {
			testDifficulty = request.getParameter(TEST_DIFFICULTY);
		} else {
			testDifficulty = (String) session.getAttribute(TEST_DIFFICULTY);
		}
		if (request.getParameter(TEST_TIME) != null) {
			testTime = request.getParameter(TEST_TIME);
		} else {
			testTime = (String) session.getAttribute(TEST_TIME);
		}

		List<Questions> qlist = db.findQuestionsByTestId(testId);

		session.setAttribute(TEST_NAME, testName);
		session.setAttribute(TEST_DIFFICULTY, testDifficulty);
		session.setAttribute(TEST_TIME, testTime);
		session.setAttribute("questionList", qlist);
		session.setAttribute(TEST_ID,
				testId);

		page = Path.EDIT_TEST_PAGE;

		return page;
	}

}
