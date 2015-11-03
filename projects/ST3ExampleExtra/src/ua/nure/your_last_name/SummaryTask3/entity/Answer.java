package ua.nure.your_last_name.SummaryTask3.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Implements the Answer entity.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Answer", propOrder = { "content" })
public class Answer {

	@XmlValue
	private String content;

	@XmlAttribute
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
	
	// ////////////////////////////////////////////////////////
	// it is not good idea to add your own code to this class
	// because it has been created with the help of JAXB
	// if you need to add some functionality to this class
	// create an OTHER WRAPPER class associated with this class
	// and place all the necessary functionality to it
	
	@Override
	public String toString() {
		return content + (isCorrect() ? " [correct=true]" : "");		
	}
}
