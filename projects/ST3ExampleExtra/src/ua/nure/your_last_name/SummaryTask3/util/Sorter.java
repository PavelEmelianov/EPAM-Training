package ua.nure.your_last_name.SummaryTask3.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;
import ua.nure.your_last_name.SummaryTask3.controller.DOMController;
import ua.nure.your_last_name.SummaryTask3.entity.Answer;
import ua.nure.your_last_name.SummaryTask3.entity.Question;
import ua.nure.your_last_name.SummaryTask3.entity.Test;

/**
 * Contains static methods for sorting.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Sorter {
	
	////////////////////////////////////////////////////////////
	// these are comparators
	////////////////////////////////////////////////////////////

	/**
	 * Sorts questions by question text.
	 */
	public static final Comparator<Question> sortQuestionsByQuestionText = new Comparator<Question>() {
		@Override
		public int compare(Question o1, Question o2) {
			return o1.getQuestionText().compareTo(o2.getQuestionText());
		}
	};

	/**
	 * Sorts questions by answers number.
	 */
	public static final Comparator<Question> sortQuestionsByAnswersNumber = new Comparator<Question>() {
		@Override
		public int compare(Question o1, Question o2) {
			return o1.getAnswers().size() - o2.getAnswers().size();
		}
	};

	/**
	 * Sorts answers by content.
	 */
	public static final Comparator<Answer> sortAnswersByContent = new Comparator<Answer>() {
		@Override
		public int compare(Answer o1, Answer o2) {
			return o1.getContent().compareTo(o2.getContent());
		}
	};

	/**
	 * Sorts answers by correct.
	 */
	public static final Comparator<Answer> sortAnswersByCorrect = new Comparator<Answer>() {
		@Override
		public int compare(Answer o1, Answer o2) {
			if (o1.isCorrect() && !o2.isCorrect()) {
				return -1;
			}
			if (o2.isCorrect() && !o1.isCorrect()) {
				return 1;
			}
			return 0;
		}
	};

	////////////////////////////////////////////////////////////
	// these methods take Test object and sort it 
	// with according comparator
	////////////////////////////////////////////////////////////
	
	public static final void sortQuestionsByQuestionText(Test test) {
		Collections.sort(test.getQuestions(),
				sortQuestionsByQuestionText);
	}

	public static final void sortQuestionsByAnswersNumber(Test test) {
		Collections.sort(test.getQuestions(),
				sortQuestionsByAnswersNumber);
	}

	public static final void sortAnswersByContent(Test test) {
		for (Question question : test.getQuestions()) {
			Collections.sort(question.getAnswers(), sortAnswersByContent);
		}
	}

	public static final void sortAnswersByCorrect(Test test) {
		for (Question question : test.getQuestions()) {
			Collections.sort(question.getAnswers(), sortAnswersByCorrect);
		}
	}	

	public static void main(String[] args) throws Exception {
		// Test.xml --> Test object
		DOMController domController = new DOMController(Constants.VALID_XML_FILE);
		domController.parse(false);
		Test test = domController.getTest();
		
		System.out.println("====================================");
		System.out.println(test);
		System.out.println("====================================");
		
		System.out.println("====================================");
		Sorter.sortQuestionsByQuestionText(test);		
		System.out.println(test);		
		System.out.println("====================================");
		
		System.out.println("====================================");
		Sorter.sortAnswersByContent(test);		
		System.out.println(test);		
	}
}