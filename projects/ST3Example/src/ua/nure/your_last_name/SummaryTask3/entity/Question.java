package ua.nure.your_last_name.SummaryTask3.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Question entity.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Question {

	private String questionText;

	private List<Answer> answers;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String value) {
		this.questionText = value;
	}

	public List<Answer> getAnswers() {
		if (answers == null) {
			answers = new ArrayList<Answer>();
		}
		return answers;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(questionText).append('\n');
		for (Answer answer : answers) {
			result.append(answer).append('\n');
		}
		return result.toString();
	}
}