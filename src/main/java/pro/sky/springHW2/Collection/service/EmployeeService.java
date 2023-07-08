package pro.sky.springHW2.Collection.service;

import pro.sky.springHW2.Collection.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findCollection();
}
