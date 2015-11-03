package ua.nure.your_last_name.SummaryTask3.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Root container. Implements the Test entity.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Test {

	private List<Question> questions;

	public List<Question> getQuestions() {
		if (questions == null) {
			questions = new ArrayList<Question>();
		}
		return questions;
	}
	
	@Override
	public String toString() {
		if (questions == null || questions.size() == 0) {
			return "Test contains no questions";
		}
		StringBuilder result = new StringBuilder();
		for (Question question : questions) {
			result.append(question).append('\n');
		}
		return result.toString();
	}
}