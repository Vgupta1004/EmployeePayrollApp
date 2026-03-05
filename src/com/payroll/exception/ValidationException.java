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
