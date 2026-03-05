/**
 * Use Case 1: Employee Registration
 * 
 * Flow:
 *  - Take user input
 *  - Validate input
 *  - Create Objects
 *  - Persist Data
 *  - Display confirmation
 *  
 * @author vgup3012
 * @version 1.0
 */
package com.payroll;
import java.util.*;
import java.io.*;
import com.payroll.exception.ValidationException;
import com.payroll.entity.*;
import com.payroll.util.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
            
            UserAccount account = new UserAccount(username, password);
            Employee employee = new Employee(empId, name, email, phone, account);
            
            employee.persist();
            
            System.out.println("\nEmployee Registered Successfully:");
            System.out.println(employee.toString());
            
		}
		catch(ValidationException e) {
			System.out.println("\nValidation Failed: " + e.getMessage());
		}
		catch(IOException e) {
			System.out.println("\nError saving employee data!");
		}
		
		sc.close();

	}

}
