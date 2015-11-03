package ua.nure.emelianov.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Themes bean
 * @author Emelianov Pavel
 *
 */
public class Themes implements Serializable {
	
	private static final long serialVersionUID = -5646514999662534597L;
	
	private int id;
	private String name;
	private boolean blocked;
	
	
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
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
	@Override
	public String toString() {
		return "Themes [id=" + id + ", name=" + name + ", blocked=" + blocked
				+ "]";
	}
	
	

}
