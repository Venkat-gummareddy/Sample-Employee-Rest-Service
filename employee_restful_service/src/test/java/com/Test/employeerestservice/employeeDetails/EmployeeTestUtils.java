package com.Test.employeerestservice.employeeDetails;

import com.Test.employeerestservice.employeeDetails.EmployeeDetails;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsStatus;

public class EmployeeTestUtils {

    static EmployeeDetails makeEmployee(String firstName, EmployeeDetailsStatus status) {
        EmployeeDetails employee = new EmployeeDetails();
        employee.setFirstName(firstName);
        employee.setStatus(status);
        return employee;
    }
}
