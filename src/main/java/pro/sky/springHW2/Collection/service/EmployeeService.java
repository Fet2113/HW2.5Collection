package pro.sky.springHW2.Collection.service;

import pro.sky.springHW2.Collection.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName,double salary, int departmentId);

    Employee remove(String firstName, String lastName,double salary, int departmentId);

    Employee find(String firstName, String lastName,double salary, int departmentId);

    Collection<Employee> findCollection();
}
