package com.payroll.service;
import com.payroll.entity.*;

public class PayrollService {

	public Payslip generatePayslip(Employee employee, String month, double basic, double hra, double da, double allowances) {

		SalaryComponents sc = new SalaryComponents(basic, hra, da, allowances);

		// Gross Salary Calculation
		double gross = basic + hra + da + allowances;

		// Deductions based on demo rules
		sc.pf = basic * 0.12;  // PF (12%)
		sc.tax = gross * 0.10; // Income Tax (10%)

		// Net Pay Calculation
		sc.netPay = gross - (sc.pf + sc.tax);

		return new Payslip(
	            employee.getEmpId(), 
	            employee.getName(), 
	            month, 
	            sc.netPay
	        );
	}

}
