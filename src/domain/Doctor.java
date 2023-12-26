package domain;

import domain.Employee;

public class Doctor extends Employee {

    public Doctor(String employeeId, String name, String role) {
        super(employeeId, name, "Doctor");
    }
}
