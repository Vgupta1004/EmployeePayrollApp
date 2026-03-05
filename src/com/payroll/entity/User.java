package com.payroll.entity;
import com.payroll.util.*;

public abstract class User {
	protected String username;
	protected String passwordHash;
	protected String role;
	
	/**
	 * Constructor initializes shared user data and immediately hashes the password
	 */
	public User(String username, String password, String role) {
		this.username = username;
		this.passwordHash = PasswordUtil.hash(password);
		this.role = role;
	}
	
	public abstract boolean authenticate(String username, String password);
	
	public String getRole() {
		return role;
	}
	
	public String getUsername() {
		return username;
	}
	
}
