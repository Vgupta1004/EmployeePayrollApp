package com.payroll.entity;

public class SalaryComponents {
	
	//Earnings
	public double basicSalary;
    public double hra;
    public double da;
    public double allowances;
    
    //Deductions and Net Pay
    public double pf;
    public double tax;
    public double netPay;

	public SalaryComponents(double basicSalary, double hra, double da, double allowances) {
		this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.allowances = allowances;
	}

}
