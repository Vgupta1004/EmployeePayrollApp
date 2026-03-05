package com.payroll.entity;
import java.util.*;

/**
 * Final class for Immutability
 * Implements Cloneable to create safe copies for download
 */
public final class Payslip implements Cloneable{
	
	private final String empId;
    private final String empName;
    private final String month;
    private final double netPay;

	public Payslip(String empId, String empName, String month, double netPay) {
		this.empId = empId;
        this.empName = empName;
        this.month = month;
        this.netPay = netPay;
	}
	
	public String getEmpId() { return empId; }
    public String getMonth() { return month; }
    
    /**
     * Creates a deep/safe copy of the payslip.
     */
    @Override
    public Object clone() {
        return new Payslip(this.empId, this.empName, this.month, this.netPay);
    }
    
    /**
     * Checks equality based on EmpID and Month.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payslip payslip = (Payslip) o;
        return Objects.equals(empId, payslip.empId) && Objects.equals(month, payslip.month);
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + empId.hashCode();
        result = 31 * result + month.hashCode();
        return result;
    }
	
    @Override
    public String toString() {
        return "PAYSLIP\n" +
               "Employee ID : " + empId + "\n" +
               "Employee Name: " + empName + "\n" +
               "Month       : " + month + "\n" +
               "Net Pay     : " + netPay + "\n"; // [cite: 851]
    }

}
