package ua.nure.your_last_name.Practice9.web.logic.client;

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

@WebServlet("/ListClients")
public class ListClients extends HttpServlet {

	private static final long serialVersionUID = 6561404304661475438L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserBean> beans = null;
		try {
			DBManager dbManager = DBManager.getInstance();			
			beans = dbManager.findUserBeansByRole("client");			
		} catch (DBException ex) {
			ex.printStackTrace();
			request.setAttribute("errors", Arrays.asList("Cannot list clients: " + ex.getMessage()));
			Utils.forward("ErrorPage", request, response);
		}
		request.setAttribute("beans", beans);
		Utils.forward("/ClientView", request, response);
	}

}
