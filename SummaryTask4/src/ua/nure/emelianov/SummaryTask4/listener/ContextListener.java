package ua.nure.emelianov.SummaryTask4.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Context listener.
 * 
 * @author Emelianov Pavel
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		
		log("Servlet context destruction starts");
		log("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		
    	ServletContext context = event.getServletContext();
    	String localesFileName = context.getInitParameter("locales");
    	
    	String localesFileRealPath = context.getRealPath(localesFileName);

    	Properties locales = new Properties();
    	InputStream is = null;
    	try {
			is =new FileInputStream(localesFileRealPath);
		} catch (FileNotFoundException e1) {
			LOG.error("Cannot create inputStream", e1);
		}
    	
    	try {
			locales.load(is);
		} catch (IOException e) {
			LOG.error("Cannot configure Log4j", e);
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				LOG.error("Cannot close inputStream", e);
			}
		}

    	context.setAttribute("locales", locales);
		
		log("Servlet context initialization starts");

		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
	
		log("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(
				servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			LOG.error("Cannot configure Log4j", ex);
		}		
		log("Log4J initialization finished");
	}
	
	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initCommandContainer() {
		
		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.emelianov.SummaryTask4.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot initialize Command Container");
		}
	}
	
	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}