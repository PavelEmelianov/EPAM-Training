package ua.nure.your_last_name.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.your_last_name.SummaryTask4.Path;
import ua.nure.your_last_name.SummaryTask4.db.DBManager;
import ua.nure.your_last_name.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.your_last_name.SummaryTask4.exception.AppException;

/**
 * Lists orders.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ListOrdersCommand extends Command {

	private static final long serialVersionUID = 1863978254689586513L;
	
	private static final Logger LOG = Logger.getLogger(ListOrdersCommand.class);
	
	/**
	 * Serializable comparator used with TreeMap container. When the servlet
	 * container tries to serialize the session it may fail because the session
	 * can contain TreeMap object with not serializable comparator.
	 * 
	 * @author D.Kolesnikov
	 * 
	 */
	private static class CompareById implements Comparator<UserOrderBean>, Serializable {
		private static final long serialVersionUID = -1573481565177573283L;

		public int compare(UserOrderBean bean1, UserOrderBean bean2) {
			if (bean1.getId() > bean2.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
	private static Comparator<UserOrderBean> compareById = new CompareById();
			
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");
				
		List<UserOrderBean> userOrderBeanList = DBManager.getInstance().getUserOrderBeans();
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);
		
		Collections.sort(userOrderBeanList, compareById);
		
		// put user order beans list to request
		request.setAttribute("userOrderBeanList", userOrderBeanList);		
		LOG.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);
		
		LOG.debug("Commands finished");
		return Path.PAGE_LIST_ORDERS;
	}

}