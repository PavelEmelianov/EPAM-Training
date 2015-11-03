package ua.nure.your_last_name.Practice9.db.bean;

import ua.nure.your_last_name.Practice9.db.entity.User;

public class UserBean extends User {

	private static final long serialVersionUID = 1L;
	
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserBean [roleName=" + roleName + ", getLogin()=" + getLogin()
				+ ", getEmail()=" + getEmail() + ", getRoleId()=" + getRoleId()
				+ ", getFullName()=" + getFullName() + "]";
	}

}