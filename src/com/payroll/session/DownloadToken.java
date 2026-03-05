package com.payroll.session;

public class DownloadToken {
	
	private long createdTime;
    private long expiryMillis;

	public DownloadToken() {
		this.createdTime = System.currentTimeMillis();
        this.expiryMillis = 60 * 1000;
	}
	
	public boolean isExpired() {
        return (System.currentTimeMillis() - createdTime) > expiryMillis;
    }

}
