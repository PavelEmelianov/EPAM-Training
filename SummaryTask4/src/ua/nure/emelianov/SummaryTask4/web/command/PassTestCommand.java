package ua.nure.emelianov.SummaryTask4.web.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.emelianov.SummaryTask4.Path;

import ua.nure.emelianov.SummaryTask4.db.DBManager;

import ua.nure.emelianov.SummaryTask4.db.entity.Answers;
import ua.nure.emelianov.SummaryTask4.db.entity.Questions;

import ua.nure.emelianov.SummaryTask4.exception.AppException;

/***
 * Class contains functionality that allows user to pass the test and save the result
 * @author Pavel Emelianov
 *
 */
public class PassTestCommand extends Command {
	
	private static final String ANSWERED_QUESTIONS = "answeredQuestions";
	private static final String ANSWER_LIST = "answerList";
	private static final String TEST_ID = "testId";
	private static final String QUESTION_ID="questionId";
	private static final String START_TIME="startTime";

	private static final long serialVersionUID = 2206528158834207197L;

	private static final Logger LOG = Logger.getLogger(PassTestCommand.class);


	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.ERROR_PAGE;

		HttpSession session = request.getSession(false);

		long startTime = 0;

		if (session.getAttribute(START_TIME) != null) {
			startTime = (Long) session.getAttribute(START_TIME);
		} else {
			startTime = System.currentTimeMillis();
		}
		int testId;

		int questionId;

		Map<Integer, String[]> answers;

		List<Integer> answeredQuestions;

		DBManager db = DBManager.getInstance();

		if (session.getAttribute(ANSWER_LIST) != null) {
			answers = (Map) session.getAttribute(ANSWER_LIST);
		} else {
			answers = new TreeMap<Integer, String[]>();
		}

		if (session.getAttribute(ANSWERED_QUESTIONS) != null) {
			answeredQuestions = (List) session
					.getAttribute(ANSWERED_QUESTIONS);
		} else {
			answeredQuestions = new ArrayList<Integer>();
		}

		String[] answerNames = null;

		if (request.getParameter(QUESTION_ID) != null) {

			questionId = Integer.valueOf(request.getParameter(QUESTION_ID));

		} else {
			questionId = (Integer) session
					.getAttribute(QUESTION_ID);
		}

		if (request.getParameter(TEST_ID) != null) {

			testId = Integer.valueOf(request.getParameter(TEST_ID));

		} else {

			if (request.getParameterValues("answer") != null) {
				answerNames = request.getParameterValues("answer");
			}

			testId = (Integer) session.getAttribute(TEST_ID);
		}

		long time = db.findTimeForTestByTestId(testId) * 1000;

		List<Questions> questionList = db.findQuestionsByTestId(testId);

		if (questionList.size() == 0) {
			throw new AppException("Test contains no questions");
		}

		for (Questions questions : questionList) {
			if (db.getCorrectAnswers(questions.getId()).size() == 0) {
				throw new AppException("Question: " + questions.getName()
						+ " contains no correct answers");
			}
		}
		if (request.getParameter("next") != null) {

			LOG.trace(System.currentTimeMillis() - startTime);
			LOG.trace(time);

			if (System.currentTimeMillis() - startTime > time) {

				request.setAttribute("timeout", true);

			}

			questionId += 1;

			if (questionId == questionList.size()) {
				questionId = 0;
			} else if (questionId < 0) {
				questionId = questionList.size() - 1;
			}

			while (answeredQuestions.contains(questionId)) {
				questionId += 1;
			}

			session.setAttribute("startTime", startTime);
			session.setAttribute(ANSWER_LIST, answers);
			session.setAttribute(ANSWERED_QUESTIONS, answeredQuestions);

		} else if (request.getParameter("previous") != null) {

			if (System.currentTimeMillis() - startTime > time) {

				request.setAttribute("timeout", true);

			}

			questionId -= 1;

			if (questionId == questionList.size()) {
				questionId = 0;
			} else if (questionId < 0) {
				questionId = questionList.size() - 1;
			}

			while (answeredQuestions.contains(questionId)) {
				questionId -= 1;
			}

			session.setAttribute(START_TIME, startTime);
			session.setAttribute(ANSWERED_QUESTIONS, answeredQuestions);
			request.setAttribute(START_TIME, startTime);

		} else if (request.getParameter("commit") != null) {

			if (System.currentTimeMillis() - startTime > time) {

				request.setAttribute("timeout", true);

			}

			answeredQuestions.add(questionId);

			answers.put(questionList.get(questionId).getId(), answerNames);
			
			if (questionList.size() != answeredQuestions.size()) {
				questionId += 1;

				while (answeredQuestions.contains(questionId)) {
					questionId += 1;
				}

			}
			session.setAttribute(ANSWER_LIST, answers);
			session.setAttribute(ANSWERED_QUESTIONS, answeredQuestions);
			session.setAttribute(START_TIME, startTime);

		} else if (request.getParameter("end") != null) {

			double result;

			Set<Integer> resultList = new TreeSet<Integer>();

			for (Map.Entry<Integer, String[]> entry : answers.entrySet()) {
				List<Answers> correct = db.getCorrectAnswers(entry.getKey());
				List<String> names = new ArrayList<String>();
					
				
				for (Answers answer : correct) {
					names.add(answer.getName());

				}
				if (entry.getValue() == null) {
					continue;
				}

				if ((Arrays.asList(entry.getValue())).containsAll(names)) {
					resultList.add(entry.getKey());
				}
			}
			LOG.trace("resultSet size: " + resultList.size());
			result = (double) resultList.size() / questionList.size() * 100;

			db.createUsersTests(testId,
					(Integer) session.getAttribute("userId"), result);

			session.setAttribute(ANSWERED_QUESTIONS, null);
			session.setAttribute("singleQuestion", null);
			session.setAttribute("questionCount", null);
			session.setAttribute(TEST_ID, null);
			session.setAttribute(QUESTION_ID, null);
			request.setAttribute("question", null);
			session.setAttribute("answers", null);
			session.setAttribute("questions", null);
			session.setAttribute(ANSWER_LIST, null);
			session.removeAttribute(START_TIME);

			page = Path.TESTS_PAGE;
			return page;

		}

		if (questionId == questionList.size()) {
			questionId = 0;
			while (answeredQuestions.contains(questionId)) {
				questionId += 1;
			}
		} else if (questionId < 0) {
			questionId = questionList.size() - 1;
			while (answeredQuestions.contains(questionId)) {
				questionId -= 1;
			}
		}
		List<Answers> answerList = db.findAnswersByQuestionId(questionList.get(
				questionId).getId());

		// list of answered questions
		session.setAttribute("answeredQuestions", answeredQuestions);

		// attribute required for blocking "next" and "previous" buttons when
		// one question left
		session.setAttribute("singleQuestion", questionList.size()
				- answeredQuestions.size());

		// attribute required for display question count
		session.setAttribute("questionCount", questionList.size());

		// attribute required for determining test id
		session.setAttribute(TEST_ID, testId);

		// real question id from database
		session.setAttribute(QUESTION_ID, questionId);

		// question id of a question from the list of questions with current
		// test id
		request.setAttribute("question", questionList.get(questionId));

		// list of answers of the curent questions
		session.setAttribute("answers", answerList);

		// list of questions of the current test
		session.setAttribute("questions", questionList);

		page = Path.QUESTIONS_PAGE;
		return page;
	}
}
