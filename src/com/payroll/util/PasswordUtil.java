package com.payroll.util;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * This utility class handles password hashing
 */
public class PasswordUtil {
	
	/**
	 * Uses SHA-256 to hash passwords
	 * 
	 * 
	 * @param password
	 * @return
	 */
	public static String hash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = md.digest(
					password.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
			for(byte b: encodedhash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length()==0) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Hashing algorithm not found", e);
		}
	}
	
	public static boolean verify(String inputPassword, String storedHash) {
		if(inputPassword == null || storedHash == null) return false;
		
		String inputHash = hash(inputPassword);
		return inputHash.equals(storedHash);
	}
}
