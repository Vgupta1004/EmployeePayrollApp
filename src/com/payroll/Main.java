/**
 * Use Case 2: Employee Authentication
 * 
 * Flow:
 *  - Register user
 *  - Trigger Login
 *  - Receive Session
 *  - Validate Session state
 *  
 * @author vgup3012
 * @version 2.0
 */
package com.payroll;
import java.util.*;
import java.io.*;
import com.payroll.exception.ValidationException;
import com.payroll.entity.*;
import com.payroll.util.*;
import com.payroll.session.*;
import com.payroll.service.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, User> appUsers = new HashMap<>();
		
		System.out.println("-----Use Case 1: Employee Registration-----");
		
		try {
			System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            Validator.validateEmpId(empId);
            
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Validator.validateEmail(email);
            
            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            Validator.validatePhone(phone);
            
            System.out.print("Create Username: ");
            String username = sc.nextLine();
            
            System.out.print("Create Password: ");
            String password = sc.nextLine();
            
            System.out.print("Register as (1. Employee / 2. Manager): ");
            int roleChoice = Integer.parseInt(sc.nextLine());
            
            if (roleChoice == 2) {
                appUsers.put(username, new Manager(username, password));
            } else {
                appUsers.put(username, new RegularEmployee(username, password));
            }
            
            UserAccount account = new UserAccount(username, password);
            Employee employee = new Employee(empId, name, email, phone, account);
            
            employee.persist();
            
            System.out.println("\nEmployee Registered Successfully:");
            System.out.println(employee.toString());
            
            System.out.println("\n----- Use Case 2: Employee Login -----");
            AuthenticationService auth = new AuthenticationService(appUsers);
            
            Session session = auth.login();
            
            if (session != null && !session.isExpired()) {
                    System.out.println("\nLogin Successful!");
                    System.out.println(session.toString()); // Displays active user [cite: 394]
                    System.out.println("Session active and valid.");
            }
            		
            
		}
		catch(ValidationException e) {
			System.out.println("\nValidation Failed: " + e.getMessage());
		}
		catch(IOException e) {
			System.out.println("\nError saving employee data!");
		}
		catch (NumberFormatException e) {
            System.out.println("\nInvalid role selection format.");
        }
		
		sc.close();

	}

}
