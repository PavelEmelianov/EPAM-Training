package ua.nure.emelianov.SummaryTask4;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains fuctionality for e-mail validation
 * @author Emelianov Pavel
 *
 */

public class ValidEmails {

		private static final String MAIL_RU = "@mail.ru";
		private static final String GMAIL_RU = "@gmail.ru";
		private static final String GOOGLE_COM = "@google.com";
		
		private static final List<String> VALID_MAIL = new ArrayList<String>();
		
		static{
			VALID_MAIL.add(MAIL_RU);
			VALID_MAIL.add(GMAIL_RU);
			VALID_MAIL.add(GOOGLE_COM);		
		}


	public static List<String> getValidMail() {
		return VALID_MAIL;
	}
	

}
