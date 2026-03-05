/**
 * This class represents a validation related problem
 * 
 * This is a custom exception class which extends Exception
 * to throw when the user input is invalid
 */
package com.payroll.exception;

public class ValidationException extends Exception{

	public ValidationException(String message) {
		super(message);
	}
}

class EmailValidationException extends ValidationException {
    public EmailValidationException(String message) { super(message); }
}

class PhoneValidationException extends ValidationException {
    public PhoneValidationException(String message) { super(message); }
}

class EmployeeIdValidationException extends ValidationException {
    public EmployeeIdValidationException(String message) { super(message); }
}

class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) { super(message); }
}