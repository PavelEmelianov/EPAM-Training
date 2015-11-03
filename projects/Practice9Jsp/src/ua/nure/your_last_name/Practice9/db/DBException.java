package ua.nure.your_last_name.Practice9.db;

/**
 * An exception that provides information on a DB access error or other DB
 * errors.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class DBException extends Exception {

	private static final long serialVersionUID = 389173242126701781L;

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
