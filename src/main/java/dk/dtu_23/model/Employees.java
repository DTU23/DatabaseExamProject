package dk.dtu_23.model;

import java.util.List;

/**
 * Created by viktor on 03-04-17.
 */
public class Employees
{
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}