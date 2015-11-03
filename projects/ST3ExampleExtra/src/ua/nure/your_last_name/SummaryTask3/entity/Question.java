package ua.nure.your_last_name.SummaryTask3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Implements the Question entity.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Question", propOrder = { "questionText", "answers" })
public class Question {

	@XmlElement(name = "QuestionText", required = true)
	private String questionText;

	@XmlElement(name = "Answer", required = true)
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

	// ////////////////////////////////////////////////////////
	// it is not good idea to add your own code to this class
	// because it has been created with the help of JAXB
	// if you need to add some functionality to this class
	// create an OTHER WRAPPER class associated with this class
	// and place all the necessary functionality to it

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(questionText).append('\n');
		for (Answer answer : answers) {
			result.append(answer).append('\n');
		}
		return result.toString();
	}
}
