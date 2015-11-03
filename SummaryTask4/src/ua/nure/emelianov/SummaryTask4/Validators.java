package ua.nure.emelianov.SummaryTask4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class contains validators for all the input fields in the application
 * @author Joan
 *
 */
public class Validators {

	private static final String INVALID_SYMBOLS = "\" has invalid symbols";
	private static final String EMPTY = "\" cannot be empty";
	private static final String FIELD = "field \"";
	private static final String LONG = "\" is too long";
	
	/**
	 * 
	 * @param email e-mail
	 * @param length maximum length of the variable in database
	 * @return error message or null if validation was successful
	 */

	public static String validateEMail(String email, int length) {

		if (email == null) {

			return FIELD + "e-mail" + EMPTY;

		} else if (email.length() > length) {
			return FIELD + "e-mail" + LONG;
		}
		List<String> list = ValidEmails.getValidMail();
		for (String mail : list) {
			Pattern p = Pattern.compile("[A-Za-z\\d]+" + mail + "$");
			Matcher m = p.matcher(email);
			if (m.find()) {
				return null;
			}
		}
		return FIELD + "e-mail" + INVALID_SYMBOLS;
	}
/**
 * 
 * @param field value of the input field
 * @param fieldName name of the input field
 * @param length maximum length of the variable in database
 * @return error message or null if validation was successful
 */
	public static String validateFields(String field, String fieldName,
			int length) {

		if (field.length() == 0) {
			return FIELD + fieldName + EMPTY;
		} else if (field.length() > length) {
			return FIELD + fieldName + LONG;
		}

		Pattern p = Pattern.compile("[Р-пр-џA-Za-z\\d]+");
		Matcher m = p.matcher(field);

		if (!m.find()) {
			return FIELD + fieldName + INVALID_SYMBOLS;
		}
		if (m.group().length() != field.length()) {
			return FIELD + fieldName + INVALID_SYMBOLS;
		}

		return null;
	}
	
	/**
	 * Validator for 'test name' 'question name' 'answer name' input fields
	 * @param field value of the input field
	 * @param fieldName name of the input field
	 * @param length maximum length of the variable in database
	 * @return error message or null if validation was successful
	 */

	public static String validateSentences(String field, String fieldName,
			int length) {

		if (field.length() == 0) {
			return FIELD + fieldName + EMPTY;
		} else if (field.length() > length) {
			return FIELD + fieldName + LONG;
		}

		Pattern p = Pattern.compile("[Р-пр-џA-Za-z\\d\\s\\W]+");
		Matcher m = p.matcher(field);

		if (!m.find()) {
			return FIELD + fieldName + INVALID_SYMBOLS;
		}
		if (m.group().length() != field.length()) {
			return FIELD + fieldName + INVALID_SYMBOLS;
		}

		p = Pattern.compile("[ ]+");
		m = p.matcher(field);

		if (m.find() && m.group().length() == field.length()) {

			return FIELD + fieldName + INVALID_SYMBOLS;

		}

		return null;
	}
	
	/**
	 * validator for input fields that must contain only latin symbols
	 * @param field value of the field
	 * @param fieldName name of the field
	 * @param length maximum length of the variable in database
	 * @return error message or null if validation was successful
	 */

	public static String validateOnlyLatinFields(String field,
			String fieldName, int length) {

		if (field.length() == 0) {
			return FIELD + fieldName + EMPTY;
		} else if (field.length() > length) {
			return FIELD + fieldName + LONG;
		}

		Pattern p = Pattern.compile("[A-Za-z\\d]+");
		Matcher m = p.matcher(field);

		if (!m.find()) {
			return FIELD + fieldName + INVALID_SYMBOLS;
		}
		if (m.group().length() != field.length()) {
			return FIELD + fieldName + INVALID_SYMBOLS;
		}

		return null;
	}
	
	/**
	 * validator for input fields that must contain only numbers
	 * @param field value of the field
	 * @param fieldName name of the field
	 * @param length maximum length of the variable in database
	 * @return error message or null if validation was successful
	 */
	
	public static String validateNumbers(String number, String fieldName,
			int length) {

		if (number.length() == 0) {
			return FIELD + fieldName + EMPTY;
		} else if (number.length() > length) {
			return FIELD + fieldName + LONG;
		}

		Pattern p = Pattern.compile("[\\d]+");
		Matcher m = p.matcher(number);
		if (m.find() && number.length() == m.group().length()) {

			return null;

		}

		return FIELD + fieldName + INVALID_SYMBOLS;
	}

/**
 * validator for test difficulty input field
 * @param difficulty difficulty of the field
 * @return error message or null if validation was successful
 */
	public static String validateDifficulty(String difficulty) {

		if (difficulty == null) {

			return FIELD + "difficulty" + EMPTY;
		}

		List<String> list = new ArrayList<String>();
		list.add("elementary");
		list.add("advanced");
		list.add("proficient");

		if (list.contains(difficulty)) {
			return null;
		}

		return "invalid difficulty, only \"elementary\", \"advanced\",\"proficient\" are available";
	}

}
