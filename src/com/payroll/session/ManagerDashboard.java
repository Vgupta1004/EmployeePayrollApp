package com.payroll.session;
import com.payroll.entity.*;
import java.util.*;

public class ManagerDashboard implements Dashboard{

	@Override
	public void display(ArrayList<Payslip> payslips, Employee employee) {
		System.out.println("=== MANAGER DASHBOARD ===");

		double teamTotal = 0;
		for (Payslip p : payslips) {
			teamTotal += p.getNetPay();
		}
		System.out.println("Team Total YTD Earnings: " + teamTotal);
	}

}
