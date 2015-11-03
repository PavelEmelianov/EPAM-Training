package ua.nure.emelianov.SummaryTask.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import ua.nure.emelianov.SummaryTask4.exception.AppException;


/**
 * Class contains functionality for password hashing
 * @author Emelianov Pavel
 *
 */
public final class Password {
	
	private static final Logger LOG = Logger.getLogger(Password.class);
	
	private static final String ERROR = "Password hash error";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	/**
	 * password hash method
	 * @param str value, that must be hashed
	 * @return hashed value
	 * @throws AppException contains UnsupportedEncodingException, NoSuchAlgorithmException
	 */

	public static String hash(String str) throws AppException  {
		MessageDigest digest;
		StringBuffer hexString = new StringBuffer();
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			LOG.error(ERROR);
			throw new AppException(ERROR,e);
		}
		try {
			digest.update(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOG.error(ERROR);
			throw new AppException(ERROR,e);
		}
		for (byte d : digest.digest()) {
			hexString.append(getFirstHexDigit(d)).append(getSecondHexDigit(d));
		}
		return hexString.toString();
	}
	
	/**
	 * 
	 * @param x byte value
	 * @return hashed char value
	 */

	private static char getFirstHexDigit(byte x) {
		return HEX_DIGITS[(0xFF & x) / 16];
	}

	/***
	 * 
	 * @param x byte value
	 * @return hashed char value
	 */
	private static char getSecondHexDigit(byte x) {
		return HEX_DIGITS[(0xFF & x) % 16];
	}
}