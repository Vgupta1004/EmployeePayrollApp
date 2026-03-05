/**
 * Use Case 4: Payslip Print/Download
 * 
 * Flow:
 *  - Create Original payslip
 *  - Clone payslip for download
 *  - verify equality and identity
 *  - Check download expiry
 *  - Save Payslips to files
 *  
 * @author vgup3012
 * @version 4.0
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
		Payslip originalPayslip = null;

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

			System.out.println("\n----- Employee Login -----");
			AuthenticationService auth = new AuthenticationService(appUsers);
			Session session = auth.login();
			if (session != null && !session.isExpired()) {
				System.out.println("\n----- Payslip Generation -----");

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
				originalPayslip = payroll.generatePayslip(loggedInEmp, month, basic, hra, da, allowances);

				System.out.println("\nOriginal Payslip:");
				System.out.println(originalPayslip.toString());

				// --- UC4: Payslip Download ---
				System.out.println("\n----- Payslip Download -----");

				// 1. Clone the payslip to ensure data safety
				Payslip downloadCopy = (Payslip) originalPayslip.clone();

				// 2. Verify equality and identity
				System.out.println("Verified: Download copy is equal to original: " + downloadCopy.equals(originalPayslip));
				System.out.println("Original hashcode: " + originalPayslip.hashCode());
				System.out.println("Cloned hashcode: " + downloadCopy.hashCode());

				// 3. Check download token expiry
				DownloadToken token = new DownloadToken();
				if (!token.isExpired()) { 
					FileService fileService = new FileService();

					// 4. Save cloned copy to files 
					String txtFile = fileService.savePayslipAsText(downloadCopy); 
					String pdfFile = fileService.savePayslipAsPdf(downloadCopy); 

					System.out.println("Payslip Download Successful."); 
					System.out.println("Saved as text file: " + txtFile); 
					System.out.println("Saved as PDF file: " + pdfFile);
				} else {
					System.out.println("Download link expired!");
				}
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
