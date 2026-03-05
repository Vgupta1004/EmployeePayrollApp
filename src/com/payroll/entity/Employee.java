package com.payroll.entity;
import java.io.*;

public class Employee {
	private String empId;
	private String name;
	private String email;
	private String phone;
	private UserAccount account;

	/**
	 * Constructor is used to create a fully initialized class object
	 * 
	 * @param empId
	 * @param name
	 * @param email
	 * @param phone
	 * @param account
	 */
	public Employee(String empId, String name, String email, String phone, UserAccount account) {
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.account = account;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
     * Converts Employee data into a readable format for display
     * This avoids manual printing in the main logic
     */
	@Override
	public String toString() {
		return "EmpId: " + empId +
				"\nName: " + name +
				"\nEmail: " + email +
				"\nPhone: " + phone+
				"\n" + account.toString();
	}
	
	/**
	 * Save Employee data into file.
	 * 
	 * @throws IOException
	 */
	public void persist() throws IOException {
		try (FileWriter writer = new FileWriter("employee_data.txt", true)) {
            writer.write(this.toString() + "\n---\n");
            System.out.println("Data persisted in file: employee_data.txt");
        }
	}
}
