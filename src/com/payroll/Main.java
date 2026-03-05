/**
 * Use Case 5: Dashboard Display
 * 
 * Flow:
 *  - Prepare historical data
 *  - Request appropriate dashboard via factory
 *  - Display role-specific metrics
 *  
 * @author vgup3012
 * @version 5.0
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
	private static Map<String, String> userRoles = new HashMap<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Payslip originalPayslip = null;

		System.out.println("-----Employee Registration-----");

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

			System.out.print("Enter Role (EMPLOYEE/MANAGER): ");
			String role = sc.nextLine().toUpperCase();
			userRoles.put(name, role);

			System.out.print("Create Username: ");
			String username = sc.nextLine();

			System.out.print("Create Password: ");
			String password = sc.nextLine();

			if(role.equals("MANAGER")) {
				appUsers.put(username, new Manager(username, password));
			} else {
				appUsers.put(username, new RegularEmployee(username, password));
			}

			UserAccount account = new UserAccount(username, password);
			Employee employee = new Employee(empId, name, email, phone, account);
			employeeRecords.put(username, employee);

			employee.persist();
			System.out.println("\nEmployee Registered Successfully!");

			System.out.println("\n----- Employee Login -----");
			AuthenticationService auth = new AuthenticationService(appUsers);
			Session session = auth.login();

			if (session != null && !session.isExpired()) {

				// --- UC5: Dashboard Display ---
				System.out.println("\n----- Dashboard -----");
				Employee loggedInEmp = employeeRecords.get(username);

				// 1. Prepare historical payslip data (Required for UC5 sorting/YTD)
				ArrayList<Payslip> history = new ArrayList<>();
				history.add(new Payslip(loggedInEmp.getEmpId(), loggedInEmp.getName(), "Jan 2026", 34000.0));
				history.add(new Payslip(loggedInEmp.getEmpId(), loggedInEmp.getName(), "Feb 2026", 32000.0));
				history.add(new Payslip(loggedInEmp.getEmpId(), loggedInEmp.getName(), "Mar 2026", 33000.0));

				// 2. Select dashboard at runtime using Factory 
				Dashboard dashboard = DashboardFactory.getDashboard(userRoles.get(loggedInEmp.getName()));

				// 3. Display the dashboard
				if (dashboard != null) {
					dashboard.display(history, loggedInEmp);
				}

				// --- UC3: Payslip Generation (Current Month) ---
				System.out.println("\n----- Current Month Payslip Generation -----");
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
				System.out.println("\n----- Use Case 4: Payslip Download -----");
				Payslip downloadCopy = (Payslip) originalPayslip.clone();

				System.out.println("Verified: Download copy is equal to original: " + downloadCopy.equals(originalPayslip));

				DownloadToken token = new DownloadToken();
				if (!token.isExpired()) { 
					FileService fileService = new FileService();
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
