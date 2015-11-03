package ua.nure.emelianov.SummaryTask4.db.entity;

import java.io.Serializable;
/**
 * Questions bean
 * @author Emelianov Pavel
 *
 */
public class Questions implements Serializable {
	
	private static final long serialVersionUID = 6869239473070244799L;
	
	private int id;
	private String name;
	
	private int testsId;
	
	
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
	public int getTestsId() {
		return testsId;
	}
	public void setTestsId(int testsId) {
		this.testsId = testsId;
	}
	@Override
	public String toString() {
		return "Questions [id=" + id + ", name=" + name + ", testsId="
				+ testsId + "]";
	}

}
