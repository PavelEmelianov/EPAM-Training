package ua.nure.emelianov.SummaryTask4.customTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

/***
 * Class contains functionality that converts seconds to hours
 * @author Emelianov Pavel
 *
 */
public class FormatToHour extends SimpleTagSupport {

	private static final Logger LOG = Logger.getLogger(FormatToMin.class);

	private String number;

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public void doTag() throws JspException, IOException {

		LOG.trace("Number: " + number);
		
		try {
			getJspContext().getOut().write(String.valueOf(Integer.valueOf(number)/3600));
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
}
