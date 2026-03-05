package com.payroll.session;
import com.payroll.entity.*;
import java.util.*;

public class EmployeeDashboard implements Dashboard{

	@Override
	public void display(ArrayList<Payslip> payslips, Employee employee) {
		System.out.println("=== EMPLOYEE DASHBOARD ===");
		System.out.println("Welcome, " + employee.getName());

		// Sort payslips in descending order of net pay using a Comparator
		Collections.sort(payslips, new Comparator<Payslip>() {
			public int compare(Payslip p1, Payslip p2) {
				return (int) (p2.getNetPay() - p1.getNetPay());
			}
		});

		System.out.println("\nRecent Payslips (Top 3):");
		for (int i = 0; i < Math.min(payslips.size(), 3); i++) {
			System.out.println(payslips.get(i).getMonth() + ": " + payslips.get(i).getNetPay());
		}

		// Calculate YTD Earnings
		double totalYTD = 0;
		for (Payslip p : payslips) {
			totalYTD += p.getNetPay();
		}
		System.out.println("Year-To-Date Earnings: " + totalYTD);
	}
}
