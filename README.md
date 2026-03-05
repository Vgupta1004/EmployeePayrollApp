# EmployeePayrollApp

## Use Case 1: Employee Registration
### Description
This is used to Register a new employee with validated personal and salary information.
The Flow -
 - take input from user
 - Validate input
 - Create Objects
 - Persist data
 - Display Confirmation

## Use Case 2: Employee Authentication and Login
### Description
This is used to securely authenticate user and grant access to the dashboard
The flow -
 - Registration of the user
 - Hash Verification
 - Session creation
 - Dashboard

## Use Case 3: Payslip Generation
### Description
This is used to Generate detailed monthly payslip breakdown
The flow -
 - Select Month
 - Calculate components
 - Format
 - Display

## Use Case 4: Payslip Print/Download
### Description
This is used to Generate downloadable payslip copy
The flow -
 - Create original payslip
 - Clone payslip for download
 - Verify equality and identity
 - Check download expiry
 - Save payslip to files
 - Print cloned payslip

## Use Case 5: Dashboard Display
### Description
This is used to Display personalized payroll dashboard.
The flow -
 - Prepare historical data
 - Request appropriate dashboard via factory
 - Display role-specific metrics

## Use Case 6: Input Validation Service
### Description
This is used to Validate all user inputs robustly.
The flow -
 - Read raw user inputs from the console
 - Delegate each input to the ValidationService.
 - Stop immediately (fail-fast) if a ValidationException is thrown.
 - Proceed only when all inputs are confirmed as valid.
 
