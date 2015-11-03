package ua.nure.emelianov.SummaryTask4.db.entity;

import java.io.Serializable;
/**
 * Users bean
 * @author Emelianov Pavel
 *
 */
public class Users implements Serializable {

	private static final long serialVersionUID = 8317306764596385940L;
	
	private int id;
	private String login;
	private String password;
	private String fName;
	private String lName;
	private String eMail;
	private boolean blocked;
	private int roleId;

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.blocked = isBlocked;
	}


	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getLogin() {
		return login;
	}

	public final void setLogin(String login) {
		this.login = login;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getfName() {
		return fName;
	}

	public final void setfName(String fName) {
		this.fName = fName;
	}

	public final String getlName() {
		return lName;
	}

	public final void setlName(String lName) {
		this.lName = lName;
	}

	public final int getRoleId() {
		return roleId;
	}

	public final void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", login=" + login + ", password="
				+ password + ", fName=" + fName + ", lName=" + lName
				+ ", roleId=" + roleId + ", status=" + blocked;
	}

}
