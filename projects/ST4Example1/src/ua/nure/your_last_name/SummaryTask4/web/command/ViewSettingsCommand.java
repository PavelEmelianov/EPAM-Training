package ua.nure.your_last_name.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.your_last_name.SummaryTask4.Path;

/**
 * View settings command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ViewSettingsCommand extends Command {
	
	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {		
		LOG.debug("Command starts");

		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}