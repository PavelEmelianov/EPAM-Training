package ua.nure.your_last_name.SummaryTask3.entity;

/**
 * Implements the Answer entity.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Answer {

	private String content;

	private Boolean correct;

	public String getContent() {
		return content;
	}
	
	public void setContent(String value) {
		this.content = value;
	}

	public boolean isCorrect() {
		if (correct == null) { 
			return false;
		}
		return correct;
	}

	public void setCorrect(Boolean value) {
		this.correct = value;
	}
	
	@Override
	public String toString() {
		return content + (isCorrect() ? " [correct=true]" : "");		
	}
}
