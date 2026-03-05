package com.payroll.session;
import com.payroll.entity.*;
import java.util.*;

public interface Dashboard {
	void display(ArrayList<Payslip> payslips, Employee employee);
}
