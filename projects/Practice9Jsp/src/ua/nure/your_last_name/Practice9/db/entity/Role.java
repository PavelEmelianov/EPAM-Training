package ua.nure.your_last_name.Practice9.db.entity;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1502216056119643221L;

	public static final String ROLE_ADMIN = "admin";

	public static final String ROLE_CLIENT = "client";

	private int id;

	private String name;

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
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
