package pro.sky.springHW2.Collection.service;

import org.springframework.stereotype.Service;
import pro.sky.springHW2.Collection.exeption.EmployeeAlreadyAddedException;
import pro.sky.springHW2.Collection.exeption.EmployeeNotFoundException;
import pro.sky.springHW2.Collection.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    private final static int MAX_SIZE = 2;

    @Override
    public Employee add(String firstName, String lastName) {
        if (employeeList.size() >= MAX_SIZE) {
            throw new EmployeeAlreadyAddedException("Массив сотрудников переполнен");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    @Override
    public Collection<Employee> findCollection() {
        return employeeList;
    }
}
