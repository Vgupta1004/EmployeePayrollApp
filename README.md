# EmployeePayrollApp

## Use Case 6: Input Validation Service

### Description
This is used to Validate all user inputs robustly.
The flow -
 - Read raw user inputs from the console
 - Delegate each input to the ValidationService.
 - Stop immediately (fail-fast) if a ValidationException is thrown.
 - Proceed only when all inputs are confirmed as valid.
 