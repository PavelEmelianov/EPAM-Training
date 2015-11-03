package ua.nure.your_last_name.Practice9.web.logic.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.your_last_name.Practice9.Utils;
import ua.nure.your_last_name.Practice9.db.DBException;
import ua.nure.your_last_name.Practice9.db.DBManager;
import ua.nure.your_last_name.Practice9.db.bean.UserBean;

@WebServlet("/ListUsers")
public class ListUsers extends HttpServlet {

	private static final long serialVersionUID = 8190289295835611353L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserBean> beans = null;
		try {
			DBManager dbManager = DBManager.getInstance();			
			beans = dbManager.findAllUserBeans();			
		} catch (DBException ex) {
			ex.printStackTrace();
			request.setAttribute("errors", Arrays.asList("Cannot list users: " + ex.getMessage()));
			Utils.forward("ErrorPage", request, response);
		}
		request.setAttribute("beans", beans);
		Utils.forward("/AdminView", request, response);
	}

}
