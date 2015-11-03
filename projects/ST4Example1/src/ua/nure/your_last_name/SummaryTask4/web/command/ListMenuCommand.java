package ua.nure.your_last_name.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.your_last_name.SummaryTask4.Path;
import ua.nure.your_last_name.SummaryTask4.db.DBManager;
import ua.nure.your_last_name.SummaryTask4.db.entity.MenuItem;
import ua.nure.your_last_name.SummaryTask4.exception.AppException;

/**
 * Lists menu items.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ListMenuCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListMenuCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		// get menu items list
		List<MenuItem> menuItems = DBManager.getInstance().findMenuItems();
		LOG.trace("Found in DB: menuItemsList --> " + menuItems);
		
		// sort menu by category
		Collections.sort(menuItems, new Comparator<MenuItem>() {
			public int compare(MenuItem o1, MenuItem o2) {
				return (int)(o1.getCategoryId() - o2.getCategoryId());
			}
		});		
		
		// put menu items list to the request
		request.setAttribute("menuItems", menuItems);		
		LOG.trace("Set the request attribute: menuItems --> " + menuItems);
		
		LOG.debug("Command finished");
		return Path.PAGE_LIST_MENU;
	}

}