package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.Validators;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.entity.Answers;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;

/***
 * Class contains functionality to refactor all fields that are available for refactoring in the application
 * @author Pavel Emelianov
 */
public class RefactorCommand extends Command {
	
	private static final String TEST_ID ="testId";
	private static final String TEST ="Test";
	private static final String TYPE="type";
	private static final String TIME="Time";
	private static final String ANSWER="Answer";
	private static final String QUESTION ="Question";
	private static final String QUESTION_ID="questionId";
	private static final String THEME="Theme";
	private static final String NAME="name";
	
	private static final long serialVersionUID = -5758849002013007925L;

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

		String name = request.getParameter(NAME);

		if ("theme".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, THEME, 100)!=null){
				throw new AppException(Validators.validateSentences(name, THEME, 100));
			}

else if(name.length()==0){
				throw new AppException("theme name is empty");
			}

			String themeId = String.valueOf(session.getAttribute("theme"));
			db.updateThemeName(name, themeId);
			session.setAttribute("themesList", db.getThemes());
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
		}

		else if ("test".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, "Test", 100)!=null){
				throw new AppException(Validators.validateSentences(name, TEST, 100));
			}else if(name.length()==0){
				throw new AppException("test name is empty");
			}
			
			String testId = String.valueOf(session.getAttribute(TEST_ID));
			db.updateTestName(name, testId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("testName", name);
		} else if ("testDifficulty".equals((String) session
				.getAttribute("name"))) {
			
			if (Validators.validateDifficulty(name) != null) {
				throw new AppException(
						Validators.validateDifficulty(name));
			}
			
			String testId = String.valueOf(session.getAttribute(TEST_ID));
			db.updateTestDifficulty(name, testId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("testDifficulty", name);
			
		} else if ("testTime".equals((String) session.getAttribute(NAME))) {
			
			
			if (Validators.validateNumbers(name, TIME, 10) != null) {
				throw new AppException(Validators.validateNumbers(name, "Time",
						10));
			}
			if(Integer.valueOf(name)==0){
				throw new AppException("time cannot be 0");
			}
			
			String testId = String.valueOf(session.getAttribute(TEST_ID));
			db.updateTestTime(name, testId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("testTime", name);
		}

		else if ("answer".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, ANSWER, 100)!=null){
				throw new AppException(Validators.validateSentences(name, ANSWER, 100));
			}else if(name.length()==0){
				throw new AppException("answer name is empty");
			}

			String answerId = String.valueOf(session.getAttribute("answerId"));
			db.updateAnswerName(name, answerId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
		}

		else if ("question".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, QUESTION, 100)!=null){
				throw new AppException(Validators.validateSentences(name, QUESTION, 100));
			}else if(name.length()==0){
				throw new AppException("question name is empty");
			}

			String questionId = String.valueOf(session
					.getAttribute(QUESTION_ID));
			db.updateQuestionName(name, questionId);
			request.setAttribute("type", (String) session.getAttribute(NAME));
			session.setAttribute("questionName", name);
			session.setAttribute("questionList", db.getQuestions());
		}

		else if ("addTheme".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, THEME, 100)!=null){
				throw new AppException(Validators.validateSentences(name, THEME, 100));
			}else if(name.length()==0){
				throw new AppException("theme name is empty");
			}

			db.createTheme(name);
			System.out.println((String) session.getAttribute(NAME));
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("themesList", db.getThemes());
		} else if ("addTest".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, "Test", 100)!=null){
				throw new AppException(Validators.validateSentences(name, TEST, 100));
			}else if(name.length()==0){
				throw new AppException("test name is empty");
			}

			String difficulty = request.getParameter("difficulty");
			String time = request.getParameter("time");
			int themeId = (Integer) session.getAttribute("theme");


			if (Validators.validateDifficulty(difficulty) != null) {
				throw new AppException(
						Validators.validateDifficulty(difficulty));
			}
			if (Validators.validateNumbers(time, TIME, 10) != null) {
				throw new AppException(Validators.validateNumbers(time, TIME,
						10));
			}
			if(Integer.valueOf(time)==0){
				throw new AppException("time cannot be 0");
			}
			db.createTest(name, difficulty, time, themeId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("testName", name);
			session.removeAttribute("difficulty");
			session.removeAttribute("time");
		} else if ("addQuestion".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, QUESTION, 100)!=null){
				throw new AppException(Validators.validateSentences(name, QUESTION, 100));
			}else if(name.length()==0){
				throw new AppException("question name is empty");
			}

			db.createQuestion(name, (Integer) session.getAttribute("testId"));
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("questionList", db.getQuestions());
		} else if ("addAnswer".equals((String) session.getAttribute(NAME))) {
			
			if(Validators.validateSentences(name, ANSWER, 100)!=null){
				throw new AppException(Validators.validateSentences(name, ANSWER, 100));
			}else if(name.length()==0){
				throw new AppException("answer name is empty");
			}
			
			int questionId = (Integer) session.getAttribute("questionId");
			List<Answers> answers = db
					.findAnswersByQuestionId((Integer) session
							.getAttribute(QUESTION_ID));
			for (Answers answer : answers) {
				if (answer.getName().contains(name)) {
					throw new AppException("Answer:\"" + name
							+ "\" is already exists in this question");
				}
			}
			db.createAnswer(name, questionId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
		} else if ("deleteTheme".equals((String) session.getAttribute(NAME))) {

			String themeId = String.valueOf(session.getAttribute("themeId"));
			db.deleteTheme(themeId);
			session.setAttribute("themesList", db.getThemes());
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));

		} else if ("deleteTest".equals((String) session.getAttribute(NAME))) {

			String testId = String.valueOf(session.getAttribute("testId"));
			db.deleteTest(testId);
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
			session.setAttribute("testName", name);

		} else if ("deleteQuestion".equals((String) session
				.getAttribute(NAME))) {

			String questionId = String.valueOf(session
					.getAttribute(QUESTION_ID));
			db.deleteQuestion(questionId);
			session.setAttribute("questionList", db.getQuestions());
			request.setAttribute(TYPE, (String) session.getAttribute(NAME));
		} else if ("deleteAnswer".equals((String) session.getAttribute(NAME))) {

			String answerId = String.valueOf(session.getAttribute("answerId"));
			db.deleteAnswer(answerId);
			request.setAttribute("type", (String) session.getAttribute(NAME));
		}

		session.removeAttribute(NAME);

		page = Path.ACTION_PAGE;

		return page;
	}

}
