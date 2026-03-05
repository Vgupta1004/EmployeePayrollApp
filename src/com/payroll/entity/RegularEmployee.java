package com.payroll.entity;

import com.payroll.util.PasswordUtil;

public class RegularEmployee extends User {
	
	/**
	 * @param username
	 * @param password
	 * @param role
	 */
	public RegularEmployee(String username, String password) {
		super(username, password, "EMPLOYEE");
	}

	@Override
	public boolean authenticate(String username, String password) {
		
		return this.username.equals(username) && PasswordUtil.verify(password, this.passwordHash);
	}

}
