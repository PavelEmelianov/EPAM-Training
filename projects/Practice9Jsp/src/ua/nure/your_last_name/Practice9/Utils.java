package ua.nure.your_last_name.Practice9;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Utils {

	private Utils() {
		// no op
	}

	public static boolean validateSize(String input, int size, String name,	List<String> errors) {
		if (size == 0 && (input == null || input.length() == 0)) {
			errors.add(name + " must not be empty.");
			return false;
		}
		if (size > 0 && (input == null || input.length() < size)) {
			errors.add(String.format("%s must contain %d or more characters.", name, size));
			return false;
		}
		return true;
	}

	public static boolean validatePattern(String input, String regexp, String name, List<String> errors) {
		if (!Pattern.matches(regexp, input)) {
			errors.add(String.format("%s must match regexp: %s", name, regexp));
			return false;
		}		
		return true;
	}
	
	public static void forward(String location, ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(location).forward(request, response);
	}

}
