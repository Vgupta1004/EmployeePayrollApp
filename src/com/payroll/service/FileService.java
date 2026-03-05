package com.payroll.service;
import java.io.*;
import com.payroll.entity.*;

public class FileService {
	
	/**
     * Saves payslip as a text file with a unique filename
     */
    public String savePayslipAsText(Payslip payslip) throws IOException {
        String fileName = "Payslip_" + payslip.getEmpId() + "_" + System.currentTimeMillis() + ".txt";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(payslip.toString());
        }
        return fileName;
    }
    
    /**
     * Simplified PDF simulation.
     */
    public String savePayslipAsPdf(Payslip payslip) throws IOException {
        String fileName = "Payslip_" + payslip.getEmpId() + "_" + System.currentTimeMillis() + ".pdf";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(payslip.toString());
        }
        return fileName;
    }


}
