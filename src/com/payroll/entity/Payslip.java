package com.payroll.entity;

public class Payslip {
	
	private Employee employee;          // Employee exists independently
    private SalaryComponents components; // Components owned by Payslip
    private String month;

	public Payslip(Employee employee, SalaryComponents components, String month) {
		this.employee = employee;
        this.components = components;
        this.month = month;
	}
	
	@Override
    public String toString() {
        // Formats data into a readable output [cite: 583-585]
        return "\n=========== PAYSLIP ===========" +
               "\nMonth          : " + month +
               "\nEmployee ID    : " + employee.getEmpId() +
               "\nEmployee Name  : " + employee.getName() +
               "\n---- Earnings ----" +
               "\nBasic Salary   : " + components.basicSalary +
               "\nHRA            : " + components.hra +
               "\nDA             : " + components.da +
               "\nAllowances     : " + components.allowances +
               "\n---- Deductions ----" +
               "\nPF (12%)       : " + components.pf +
               "\nTax (10%)      : " + components.tax +
               "\nNet Pay        : " + components.netPay +
               "\n==============================="; // [cite: 601-631]
    }

}
