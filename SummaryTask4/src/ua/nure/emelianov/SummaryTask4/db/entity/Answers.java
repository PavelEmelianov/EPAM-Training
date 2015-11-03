package ua.nure.emelianov.SummaryTask4.db.entity;

import java.io.Serializable;
/**
 * Answers bean
 * @author Emelianov Pavel
 *
 */
public class Answers implements Serializable{

	private static final long serialVersionUID = -4180791069565633425L;
	
	private int id;
	private String name;
	private boolean correct;
	private int questionsId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public int getQuestionsId() {
		return questionsId;
	}
	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}
	@Override
	public String toString() {
		return "Answers [id=" + id + ", name=" + name + ", correct=" + correct
				+ ", questionsId=" + questionsId + "]";
	}
	
	

}
