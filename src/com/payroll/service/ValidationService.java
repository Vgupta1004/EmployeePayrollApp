package com.payroll.service;
import com.payroll.exception.*;

public class ValidationService {

	// Sanitization ensures consistent behavior
    private static String sanitize(String input) {
        return (input == null) ? "" : input.trim();
    }

    public static void validateEmail(String email) throws ValidationException {
        String cleanEmail = sanitize(email);
        if (!cleanEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Invalid Email Format: " + cleanEmail);
        }
    }

    public static void validatePhone(String phone) throws ValidationException {
        String cleanPhone = sanitize(phone);
        // Rule: Start with 6-9, exactly 10 digits
        if (!cleanPhone.matches("^[6-9]\\d{9}$")) {
            throw new ValidationException("Invalid Phone: Must be 10 digits starting with 6-9");
        }
    }

    public static void validateEmployeeId(String empId) throws ValidationException {
        String cleanId = sanitize(empId);
        // Rule: Must follow EMP-XXXX
        if (!cleanId.matches("^EMP-\\d{4}$")) {
            throw new ValidationException("Invalid ID: Must follow EMP-XXXX format");
        }
    }

    public static void validatePassword(String password) throws ValidationException {
        // Rule: Password strength (Example: min 8 chars)
        if (password == null || password.length() < 8) {
            throw new ValidationException("Password too weak: Minimum 8 characters required");
        }
    }

}
