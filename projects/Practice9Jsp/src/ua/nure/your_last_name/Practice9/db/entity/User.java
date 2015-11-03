package ua.nure.your_last_name.Practice9.db.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -6750491987964916931L;

	private String login;

	private String password;

	private String fullName;

	private String email;

	private int roleId;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password
				+ ", fullName=" + fullName + ", email=" + email + ", roleId="
				+ roleId + "]";
	}

}
