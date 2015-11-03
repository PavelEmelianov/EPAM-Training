package ua.nure.emelianov.SummaryTask4.web.command;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.emelianov.SummaryTask4.Comparators;
import ua.nure.emelianov.SummaryTask4.Path;
import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.db.bean.UsersTests;
import ua.nure.emelianov.SummaryTask4.db.entity.Users;
import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * command gets tests results
 * @author Emelianov Pavel
 *
 */
public class GetResultCommand extends Command implements Serializable {

	private static final long serialVersionUID = -1193834127038671806L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException {

		String page = Path.TEST_RESULT_PAGE;

		HttpSession session = request.getSession(false);

		DBManager db = DBManager.getInstance();

		Users user = (Users) session.getAttribute("user");

		List<UsersTests> testList;

		if (user.getRoleId() == 0) {
			testList = db.getUsersTests();
		} else {
			testList = db.findUsersTestsByUserId((Integer) session
					.getAttribute("userId"));
		}
		
Collections.sort(testList,new Comparators.CompareByUsersTestsId());

		session.setAttribute("testsResult", testList);

		return page;
	}

}
