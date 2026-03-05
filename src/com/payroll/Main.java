/**
 * Use Case 3: Payslip Generation
 * 
 * Flow:
 *  - Select Month
 *  - Calculate components
 *  - Format
 *  - Display
 *  
 * @author vgup3012
 * @version 3.0
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
	
	private static Map<String, User> appUsers = new HashMap<>();
	private static Map<String, Employee> employeeRecords = new HashMap<>();

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
            
            appUsers.put(username, new RegularEmployee(username, password));
            UserAccount account = new UserAccount(username, password);
            Employee employee = new Employee(empId, name, email, phone, account);
            employeeRecords.put(username, employee);
            
            employee.persist();
            System.out.println("\nEmployee Registered Successfully!");
            
            System.out.println("\n----- Use Case 2: Employee Login -----");
            AuthenticationService auth = new AuthenticationService(appUsers);
            Session session = auth.login();
            if (session != null && !session.isExpired()) {
                System.out.println("\n----- Use Case 3: Payslip Generation -----");
                
                // Retrieve the actual Employee object for the logged-in user
                Employee loggedInEmp = employeeRecords.get(username);
                
                System.out.print("Enter Month: ");
                String month = sc.nextLine();
                
                System.out.print("Enter Basic Salary: ");
                double basic = Double.parseDouble(sc.nextLine());
                System.out.print("Enter HRA: ");
                double hra = Double.parseDouble(sc.nextLine());
                System.out.print("Enter DA: ");
                double da = Double.parseDouble(sc.nextLine());
                System.out.print("Enter Allowances: ");
                double allowances = Double.parseDouble(sc.nextLine());

                PayrollService payroll = new PayrollService();
                Payslip payslip = payroll.generatePayslip(loggedInEmp, month, basic, hra, da, allowances);

                // Display the final output [cite: 687, 699]
                System.out.println(payslip.toString());
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
