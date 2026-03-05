package com.payroll.util;

import com.payroll.exception.ValidationException;

/**
 * This class is responsible only for checking input correctness
 * Validation happens before objects are created
 */
public class Validator {
	
	/**
	 * Validates Email format
	 * Throws ValidationException when the format is incorrect
	 * @param email						The email user entered
	 * @throws ValidationException		The Custom exception created
	 */
	public static void validateEmail(String email) throws ValidationException {
        // Regex pattern for standard email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !email.matches(emailRegex)) {
            throw new ValidationException("Invalid Email Format: " + email);
        }
    }
	
	/**
	 * Validates Indian Phone Numbers
	 * Throws ValidationException when the format is incorrect
	 * @param phone						The phone number user entered
	 * @throws ValidationException		The Custom exception created
	 */
	public static void validatePhone(String phone) throws ValidationException{
		//Regex pattern for Indian phone number validation
		String phoneRegex = "^[6-9]\\d{9}$";
		if(phone == null || !phone.matches(phoneRegex)) {
			throw new ValidationException("Invalid Phone Format: " + phone);
		}
	}
	
	/**
	 * Validates the Employee ID format
	 * Throws ValidationException when the format is incorrect
	 * @param empId						The Employee ID user entered
	 * @throws ValidationException		The custom exception created
	 */
	public static void validateEmpId(String empId) throws ValidationException{
		//Regex pattern for Employee ID format
		String empIdRegex = "^EMP-\\d{4}$";
        if (empId == null || !empId.matches(empIdRegex)) {
            throw new ValidationException("Invalid Employee ID: Must follow EMP-XXXX");
        }
	}
	
}
