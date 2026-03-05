package com.payroll.session;

public class Session {
	private String username;
	private long loginTime;
	private long timeoutMillis = 60000;

	public Session(String username) {
		this.username = username;
		this.loginTime = System.currentTimeMillis();
	}
	
	public boolean isExpired() {
		return (System.currentTimeMillis() - loginTime) > timeoutMillis;
	}
	
	@Override
	public String toString() {
		return "Session active for user: " + username;
	}

}
