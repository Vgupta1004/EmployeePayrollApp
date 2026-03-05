package com.payroll.service;
import com.payroll.entity.*;
import com.payroll.session.*;
import java.util.*;

public class AuthenticationService {
	
	private Map<String, User> userDatabase;
    private final int maxAttempts = 3;

    public AuthenticationService(Map<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    public Session login() {
    	Scanner sc = new Scanner(System.in);
    	int attempts = 0;
    	
    	while (attempts < maxAttempts) {
            System.out.println("\n------ EMPLOYEE AUTHENTICATION ------");
            System.out.print("Enter Username: ");
            String username = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            User user = userDatabase.get(username);

            // Polymorphism: authenticate() logic depends on if 'user' is RegularEmployee or Manager
            if (user != null && user.authenticate(username, password)) {
                System.out.println("\nLogin Successful!");
                showDashboard(user.getRole());
                return new Session(username);
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts));
            }
        }
    	System.out.println("Access Denied: Maximum attempts exceeded.");
        return null;
    }
    
    private void showDashboard(String role) {
    	System.out.println("Role: " + role);
        System.out.println("---------- DASHBOARD ----------");
        if ("MANAGER".equals(role)) {
            System.out.println("Manager View: Team Analytics | Payroll Approval");
        } else {
            System.out.println("Employee View: My Payslip | Profile Settings");
        }
    }
    

}
