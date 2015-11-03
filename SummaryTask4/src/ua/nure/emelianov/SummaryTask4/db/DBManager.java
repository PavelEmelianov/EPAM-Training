package ua.nure.emelianov.SummaryTask4.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.emelianov.SummaryTask4.db.bean.UsersTests;
import ua.nure.emelianov.SummaryTask4.db.entity.Answers;
import ua.nure.emelianov.SummaryTask4.db.entity.Questions;
import ua.nure.emelianov.SummaryTask4.db.entity.Tests;
import ua.nure.emelianov.SummaryTask4.db.entity.Themes;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.DBException;

/***
 * Class for work with database
 */
public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static DBManager instance;
/**
 * singleton
 * @return DBManager object
 */
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;

	}

	private static final String NAME = "NAME";
	private static final String FIND_ALL_USERS = "SELECT * from USERS";
	private static final String FIND_ALL_THEMES = "SELECT * from THEMES";
	private static final String FIND_ALL_ANSWERS = "SELECT * from ANSWERS";
	private static final String FIND_TESTS = "SELECT * from TESTS";
	private static final String UPDATE_USER_STATUS = "UPDATE users SET blocked = ? where login=?";
	private static final String UPDATE_THEME_STATUS = "UPDATE themes SET status = ? where id=?";
	private static final String UPDATE_ANSWER_STATUS = "UPDATE answers SET correct = ? where id=?";
	private static final String GET_TESTS_BY_THEME = "SELECT * from TESTS where themes_Id=?";
	private static final String GET_ALL_QUESTIONS = "SELECT * from QUESTIONS";
	private static final String GET_QUESTIONS_BY_TEST = "SELECT * from QUESTIONS where tests_Id=?";
	private static final String GET_ANSWERS_BY_QUESTION = "SELECT * from ANSWERS where questions_Id=?";
	private static final String INSERT_USERS_TESTS = "INSERT into users_tests values(default,?,?,?,?)";
	private static final String INSERT_USERS = "INSERT into USERS values(default,?,?,?,?,?,default,1)";
	private static final String GET_ALL_USERS_TESTS = "SELECT ut.id, u.f_name, u.l_name, tm.name, t.name, t.difficulty, ut.dates, ut.result FROM users u, tests t, themes tm, users_tests ut WHERE ut.users_id=u.id AND t.id=ut.tests_id AND t.themes_id=tm.id";
	private static final String GET_USERS_TESTS = "SELECT ut.id, u.f_name, u.l_name, tm.name, t.name, t.difficulty, ut.dates, ut.result FROM users u, tests t, themes tm, users_tests ut WHERE ut.users_id=? AND ut.users_id=u.id AND t.id=ut.tests_id AND t.themes_id=tm.id";
	private static final String UPDATE_THEME_NAME = "UPDATE themes SET name = ? where id=?";
	private static final String UPDATE_TEST_NAME = "UPDATE tests SET name = ? where id=?";
	private static final String UPDATE_TEST_DIFFICULTY = "UPDATE tests SET difficulty = ? where id=?";
	private static final String UPDATE_TEST_TIME = "UPDATE tests SET time_for_test = ? where id=?";
	private static final String UPDATE_ANSWER_NAME = "UPDATE answers SET name = ? where id=?";
	private static final String UPDATE_QUESTION_NAME = "UPDATE questions SET name = ? where id=?";
	private static final String CREATE_THEME = "INSERT into THEMES values(default,?,default)";
	private static final String CREATE_TEST = "INSERT into tests values(default,?,?, default,?,?)";
	private static final String CREATE_QUESTION = "INSERT into questions values(default,?,?)";
	private static final String CREATE_ANSWER = "INSERT into answers values(default,?,false,?)";
	private static final String DELETE_THEME = "DELETE from themes where id=?";
	private static final String DELETE_TEST = "DELETE from tests where id=?";
	private static final String DELETE_QUESTION = "DELETE from questions where id=?";
	private static final String DELETE_ANSWER = "DELETE from answers where id=?";

	/***
	 * selects all users from the database
	 * @return users objects list
	 * @throws DBException if SQLexception occurred
	 */
	public List<Users> getUsers() throws DBException {

		List<Users> list = new ArrayList<Users>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(FIND_ALL_USERS);
			while (rs.next()) {
				list.add(getUser(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot get users", e);
		} finally {
			close(con, st, rs);
		}
		return list;
	}
	
	/***
	 * selects all questions from the database
	 * @return questions objects list
	 * @throws DBException if SQLexception occurred
	 */
	
	public List<Questions> getQuestions() throws DBException {

		List<Questions> list = new ArrayList<Questions>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(GET_ALL_QUESTIONS);
			while (rs.next()) {
				list.add(getQuestion(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot get questions", e);
		} finally {
			close(con, st, rs);
		}
		return list;
	}
	
	/***
	 * selects all themes from the database
	 * @return themes objects list
	 * @throws DBException if SQLexception occurred
	 */

	public List<Themes> getThemes() throws DBException {
		List<Themes> list = new ArrayList<Themes>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(FIND_ALL_THEMES);
			while (rs.next()) {
				list.add(getTheme(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot get themes", e);
		} finally {
			close(con, st, rs);
		}
		return list;

	}
	
	/***
	 * selects all answers from the database
	 * @return answers objects list
	 * @throws DBException if SQLexception occurred
	 */

	public List<Answers> getAnswers() throws DBException {
		List<Answers> list = new ArrayList<Answers>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(FIND_ALL_ANSWERS);
			while (rs.next()) {
				list.add(getAnswer(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot get answers", e);
		} finally {
			close(con, st, rs);
		}
		return list;

	}
	
	/***
	 * selects all UsersTests from the database
	 * @return UsersTests objects list
	 * @throws DBException if SQLexception occurred
	 */

	public List<UsersTests> getUsersTests() throws DBException {

		List<UsersTests> list = new ArrayList<UsersTests>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(GET_ALL_USERS_TESTS);
			while (rs.next()) {
				list.add(getUsersTests(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot find all users_tests", e);
		} finally {
			close(con, st, rs);
		}

		return list;
	}
	
	/***
	 * selects all tests from the database
	 * @return Tests objects list
	 * @throws DBException if SQLexception occurred
	 */

	public List<Tests> getTests() throws DBException {
		List<Tests> list = new ArrayList<Tests>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(FIND_TESTS);
			while (rs.next()) {
				list.add(getTest(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot find all tests", e);
		} finally {
			close(con, st, rs);
		}
		return list;
	}

	
	/**
	 * selects correct answers by questions id
	 * @param questionId question id
	 * @return Answers objects list
	 * @throws DBException if SQLExceptions occurred
	 */
	
	public List<Answers> getCorrectAnswers(int questionId) throws DBException {
		List<Answers> answers = findAnswersByQuestionId(questionId);
		List<Answers> correct = new ArrayList<Answers>();
		for (Answers answer : answers) {
			if (answer.isCorrect()) {
				correct.add(answer);
			}
		}
		return correct;
	}
	
	/**
	 * selects questions by test id
	 * @param id test id
	 * @return Questions objects list
	 * @throws DBException if SQLExceptions occurred
	 */

	public List<Questions> findQuestionsByTestId(int id) throws DBException {
		List<Questions> list = new ArrayList<Questions>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(GET_QUESTIONS_BY_TEST);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getQuestion(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot find question by test id", e);
		} finally {
			close(con, pstmt, rs);
		}

		return list;
	}
	
/**
 * selects tests by theme id
 * @param themeId theme id
 * @return Tests objects list
 * @throws DBException if SQLExceptions occurred
 */

	public List<Tests> findTestsByTheme(int themeId) throws DBException {
		List<Tests> list = new ArrayList<Tests>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(GET_TESTS_BY_THEME);
			pstmt.setInt(1, themeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getTest(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot find test by theme id", e);
		} finally {
			close(con, pstmt, rs);

		}
		System.out.println(list.size());
		return list;
	}
	
	/**
	 * selects user by login
	 * @param login user login
	 * @return Users object
	 * @throws DBException if SQLExceptions occurred
	 */

	public Users findUserByLogin(String login) throws DBException {
		Users user = null;
		for (Users u : getUsers()) {
			if (login.equals(u.getLogin())) {
				user = u;
				break;
			}

		}
		return user;

	}
	
	/**
	 * selects answer by id
	 * @param id answer id
	 * @return Answers object
	 * @throws DBException if SQLExceptions occurred
	 */

	public Answers findAnswerById(String id) throws DBException {
		Answers answer = null;
		for (Answers a : getAnswers()) {
			if (Integer.valueOf(id) == a.getId()) {
				answer = a;
				break;
			}
		}
		return answer;

	}
	
	/**
	 * selects answers by question id
	 * @param id question id
	 * @return Answers objects list
	 * @throws DBException if SQLExceptions occurred
	 */

	public List<Answers> findAnswersByQuestionId(int id) throws DBException {
		List<Answers> list = new ArrayList<Answers>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(GET_ANSWERS_BY_QUESTION);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getAnswer(rs));
			}
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot find answer by question id", e);
		} finally {
			close(con, pstmt, rs);
		}

		return list;
	}
	
	/**
	 * selects UsersTests by user id
	 * @param userId user id
	 * @return UsersTests objects list
	 * @throws DBException if SQLExceptions occurred
	 */

	public List<UsersTests> findUsersTestsByUserId(int userId)
			throws DBException {

		List<UsersTests> list = new ArrayList<UsersTests>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(GET_USERS_TESTS);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getUsersTests(rs));
			}
			con.commit();
		}

		catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot find users_tests by user id", e);
		} finally {
			close(con, pstmt, rs);
		}

		return list;
	}
	
	/**
	 * selects time for test by test id
	 * @param id test id
	 * @return time for test
	 * @throws DBException
	 */
	
	public int findTimeForTestByTestId(int id) throws DBException {
		int time = 0;
		List<Tests> tests = getTests();
		for (Tests test : tests) {
			if (test.getId() == id) {
				time = test.getTimeForTest();
			}
		}
		return time;
	}

/**
 * selects theme by id
 * @param id theme id
 * @return Themes object
 * @throws DBException if SQLExceptions occurred
 */
	public Themes findThemeById(int id) throws DBException {
		Themes theme = null;
		List<Themes> themes = getThemes();
		for (Themes tm : themes) {
			if (tm.getId() == id) {
				theme = tm;
				break;
			}
		}
		return theme;
	}

	
	/**
	 * updates user status by login
	 * @param login user login
	 * @return user status
	 * @throws DBException if SQLExceptions occurred
	 */
	public boolean updateUserStatusByLogin(String login) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		Boolean status = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_USER_STATUS);
			Users user = findUserByLogin(login);
			int k = 1;
			if (user.getBlocked()) {
				pstmt.setBoolean(k++, false);
				status = true;
			} else {
				pstmt.setBoolean(k++, true);
				status = false;
			}
			pstmt.setString(k++, user.getLogin());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update user", e);
		} finally {
			close(con);
			close(pstmt);
		}
		return status;
	}
	
	/**
	 * updates theme status by id
	 * @param id theme id
	 * @return theme status
	 * @throws DBException if SQLExceptions occurred
	 */

	public boolean updateThemeStatusById(int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		Boolean status = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_THEME_STATUS);
			Themes theme = findThemeById(id);
			int k = 1;
			if (theme.isBlocked()) {
				pstmt.setBoolean(k++, false);
				status = true;
			} else {
				pstmt.setBoolean(k++, true);
				status = false;
			}
			pstmt.setInt(k++, theme.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update theme", e);
		} finally {
			close(con);
			close(pstmt);
		}
		return status;
	}
	
	/**
	 * updates answer status by id
	 * @param id answer id
	 * @return answer status
	 * @throws DBException if SQLExceptions occurred
	 */

	public boolean updateAnswerStatusbyId(String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		Boolean status = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_ANSWER_STATUS);
			Answers answer = findAnswerById(id);
			int k = 1;
			if (answer.isCorrect()) {
				pstmt.setBoolean(k++, false);
				status = true;
			} else {
				pstmt.setBoolean(k++, true);
				status = false;
			}
			pstmt.setInt(k++, answer.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update answer", e);
		} finally {
			close(con);
			close(pstmt);
		}
		return status;
	}
	
	/**
	 * updates theme name by theme id
	 * @param name new name
	 * @param id theme id
	 * @throws DBException if SQLExceptions occurred
	 */

	public void updateThemeName(String name, String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_THEME_NAME);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setString(i++, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update theme name", e);
		} finally {
			close(con, pstmt, rs);
		}
	}
	
/**
 * updates test name by test id
 * @param name new name
 * @param id test id
 * @throws DBException if SQLExceptions occurred
 */

	public void updateTestName(String name, String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_TEST_NAME);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setString(i++, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update test name", e);
		} finally {
			close(con, pstmt, rs);
		}
	}
	
	/**
	 * updates test difficulty by test id
	 * @param difficulty new difficulty
	 * @param id test id
	 * @throws DBException if SQLExceptions occurred
	 */

	public void updateTestDifficulty(String difficulty, String id)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_TEST_DIFFICULTY);
			int i = 1;
			pstmt.setString(i++, difficulty);
			pstmt.setString(i++, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update test difficulty", e);
		} finally {
			close(con, pstmt, rs);
		}
	}
/**
 * updates time for test by test id
 * @param time new time
 * @param id test id
 * @throws DBException if SQLExceptions occurred
 */
	public void updateTestTime(String time, String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_TEST_TIME);
			int i = 1;
			pstmt.setString(i++, time);
			pstmt.setString(i++, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update test time", e);
		} finally {
			close(con, pstmt, rs);
		}
	}
/**
 * updates question name by question id
 * @param name new name
 * @param id question id
 * @throws DBException if SQLExceptions occurred
 */
	public void updateQuestionName(String name, String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_QUESTION_NAME);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setString(i++, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update question name", e);
		} finally {
			close(con, pstmt, rs);
		}
	}
/**
 * updates answer name by answer id
 * @param name new name
 * @param id answer id
 * @throws DBException if SQLExceptions occurred
 */
	public void updateAnswerName(String name, String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_ANSWER_NAME);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setString(i++, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot update answer name", e);
		} finally {
			close(con, pstmt, rs);
		}
	}
	
	/**
	 * deletes theme by theme id
	 * @param id theme id
	 * @throws DBException if SQLExceptions occurred
	 */

	public void deleteTheme(String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_THEME);
			pstmt.setInt(1, Integer.valueOf(id));
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot delete theme", e);
		} finally {
			close(con);
			close(pstmt);
		}
	}
/**
 * deletes test by test id
 * @param id test id
 * @throws DBException if SQLExceptions occurred
 */
	public void deleteTest(String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_TEST);
			pstmt.setInt(1, Integer.valueOf(id));
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot delete test", e);
		} finally {
			close(con);
			close(pstmt);
		}
	}
/**
 * deletes question by question id
 * @param id question id
 * @throws DBException if SQLExceptions occurred
 */
	public void deleteQuestion(String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_QUESTION);
			pstmt.setInt(1, Integer.valueOf(id));
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot delete question", e);
		} finally {
			close(con);
			close(pstmt);
		}
	}
	
	/**
	 * deletes answer by answer id
	 * @param id answer id
	 * @throws DBException if SQLExceptions occurred
	 */

	public void deleteAnswer(String id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_ANSWER);
			pstmt.setInt(1, Integer.valueOf(id));
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot delete answer", e);
		} finally {
			close(con);
			close(pstmt);
		}
	}
	
	/**
	 * inserts new user
	 * @param login user's login
	 * @param password user's password
	 * @param fName user's first name
	 * @param lName user's last name
	 * @param eMail user's email
	 * @throws DBException if SQLExceptions occurred
	 */

	public void createUser(String login, String password, String fName,
			String lName, String eMail) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(INSERT_USERS);
			int i = 1;
			pstmt.setString(i++, login);
			pstmt.setString(i++, password);
			pstmt.setString(i++, fName);
			pstmt.setString(i++, lName);
			pstmt.setString(i++, eMail);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot create user", e);
		} finally {
			close(con, pstmt, rs);
		}

	}
	
/**
 * inserts new UsersTests
 * @param testId test id
 * @param userId user id
 * @param result
 * @throws DBException if SQLExceptions occurred
 */

	public void createUsersTests(int testId, int userId, double result)
			throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(INSERT_USERS_TESTS);
			int i = 1;
			pstmt.setInt(i++, userId);
			pstmt.setInt(i++, testId);
			pstmt.setString(i++, new java.util.Date().toString());
			pstmt.setInt(i++, (int) result);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("Cannot create users_tests", e);
		} finally {
			close(con, pstmt, rs);
		}

	}
	
	/**
	 * inserts new theme
	 * @param name theme name
	 * @throws DBException if SQLExceptions occurred
	 */

	public void createTheme(String name) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(CREATE_THEME);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot create theme", e);
		} finally {
			close(con);
			close(pstmt);
		}

	}
	/**
	 * inserts new test
	 * @param name test's name
	 * @param difficulty test's difficulty
	 * @param time test's time
	 * @param themeId test's theme_id
	 * @throws DBException if SQLExceptions occurred
	 */

	public void createTest(String name, String difficulty, String time,
			int themeId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(CREATE_TEST);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setString(i++, difficulty);
			pstmt.setInt(i++, Integer.valueOf(time));
			pstmt.setInt(i++, themeId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot create test", e);
		} finally {
			close(con);
			close(pstmt);
		}

	}
/**
 * inserts new question
 * @param name question's name
 * @param testId question's test_id
 * @throws DBException if SQLExceptions occurred
 */
	public void createQuestion(String name, int testId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(CREATE_QUESTION);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setInt(i++, testId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot create question", e);
		} finally {
			close(con);
			close(pstmt);
		}

	}
	
	/**
	 * inserts new answer
	 * @param name answer's name
	 * @param questionId answer's question_id
	 * @throws DBException if SQLExceptions occurred
	 */

	public void createAnswer(String name, int questionId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(CREATE_ANSWER);
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setInt(i++, questionId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			throw new DBException("cannot create answer", e);
		} finally {
			close(con);
			close(pstmt);
		}

	}
/**
 * fills bean with selected values
 * @param rs ResultSet object
 * @return Answers object
 * @throws SQLException
 */
	private Answers getAnswer(ResultSet rs) throws SQLException {
		Answers answer = new Answers();
		answer.setId(rs.getInt("ID"));
		answer.setName(rs.getString(NAME));
		answer.setCorrect(rs.getBoolean("CORRECT"));
		answer.setQuestionsId(rs.getInt("QUESTIONS_ID"));
		return answer;
	}
	
	/**
	 * fills bean with selected values
	 * @param rs ResultSet object
	 * @return UsersTests object
	 * @throws SQLException
	 */

	private UsersTests getUsersTests(ResultSet rs) throws SQLException {
		UsersTests ut = new UsersTests();
		ut.setId(rs.getInt("ID"));
		ut.setfName(rs.getString("F_NAME"));
		ut.setlName(rs.getString("L_NAME"));
		ut.setTestName(rs.getString(5));
		ut.setDate(rs.getString("DATES"));
		ut.setResult(rs.getInt("RESULT"));
		ut.setThemeName(rs.getString(4));
		ut.setTestDifficulty(rs.getString("DIFFICULTY"));
		return ut;

	}
	
	/**
	 * fills bean with selected values
	 * @param rs ResultSet object
	 * @return Users object
	 * @throws SQLException
	 */

	private Users getUser(ResultSet rs) throws SQLException {
		Users user = new Users();
		user.setId(rs.getInt("ID"));
		user.setLogin(rs.getString("LOGIN"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setfName(rs.getString("F_NAME"));
		user.setlName(rs.getString("L_NAME"));
		user.seteMail(rs.getString("E_MAIL"));
		user.setRoleId(rs.getInt("ROLE_ID"));
		user.setBlocked(rs.getBoolean("BLOCKED"));
		return user;

	}
	
	/**
	 * fills bean with selected values
	 * @param rs ResultSet object
	 * @return Themes object
	 * @throws SQLException
	 */

	private Themes getTheme(ResultSet rs) throws SQLException {
		Themes theme = new Themes();
		theme.setId(rs.getInt("ID"));
		theme.setName(rs.getString(NAME));
		theme.setBlocked(rs.getBoolean("STATUS"));
		return theme;
	}
	
	/**
	 * fills bean with selected values
	 * @param rs ResultSet object
	 * @return Tests object
	 * @throws SQLException
	 */

	private Tests getTest(ResultSet rs) throws SQLException, DBException {
		Tests test = new Tests();
		test.setId(rs.getInt("ID"));
		test.setName(rs.getString(NAME));
		test.setDifficulty(rs.getString("DIFFICULTY"));
		test.setQuestionsCount(findQuestionsByTestId(test.getId()).size());
		test.setTimeForTest(rs.getInt("TIME_FOR_TEST"));
		test.setThemesId(rs.getInt("THEMES_ID"));
		return test;
	}
	
	/**
	 * fills bean with selected values
	 * @param rs ResultSet object
	 * @return Questions object
	 * @throws SQLException
	 */

	private Questions getQuestion(ResultSet rs) throws SQLException {
		Questions question = new Questions();
		question.setId(rs.getInt("ID"));
		question.setName(rs.getString(NAME));
		return question;
	}
	
	/**
	 * gets connection with DB
	 * @return Connections
	 * @throws DBException 
	 */

	private Connection getConnection() throws DBException {
		Connection con = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			con = DriverManager
					.getConnection("jdbc:derby://localhost:1527/Testing;user=pavel;password=emelianov");
		} catch (ClassNotFoundException | SQLException e) {
			LOG.error("Cannot obtain connection", e);
			throw new DBException("Cannot obtain connection", e);
		}
		return con;
	}
	
	/**
	 * closes connection,statement,resultset
	 * @param con Connection
	 * @param st Statament
	 * @param rs ResultSet
	 */

	private void close(Connection con, Statement st, ResultSet rs) {
		close(con);
		close(st);
		close(rs);

	}
	/**
	 * closes ResultSet
	 * @param rs ResultSet
	 */

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.error("Cannot close resultset", e);
			}
		}
	}
/**
 * closes Statament
 * @param st Statement
 */
	private void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				LOG.error("Cannot close statement", e);
			}
		}
	}
	
	/**
	 * closes Connection
	 * @param con Connection
	 */

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOG.error("Cannot close connection", e);
			}
		}
	}
	
	/**
	 * rollback, if transaction was failed
	 * @param con Connection
	 */

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				LOG.error("Cannot rollback transaction", e);
			}
		}
	}
}
