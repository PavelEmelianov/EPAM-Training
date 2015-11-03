package ua.nure.emelianov.SummaryTask4.web.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.emelianov.SummaryTask4.exception.AppException;
/**
 * abstract class, the base for inheritance
 * @author Emelianov Pavel
 *
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = 7389929608544179692L;

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws AppException;

}
