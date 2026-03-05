
package com.payroll.entity;
import com.payroll.util.*;

public class Manager extends User {

	/**
	 * @param username
	 * @param password
	 * @param role
	 */
	public Manager(String username, String password) {
		super(username, password, "MANAGER");
	}

	@Override
	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && PasswordUtil.verify(password, this.passwordHash);
	}

}
