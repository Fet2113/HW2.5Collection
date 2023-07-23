package pro.sky.springHW2.Collection.service;

import org.springframework.stereotype.Service;
import pro.sky.springHW2.Collection.exeption.EmployeeAlreadyAddedException;
import pro.sky.springHW2.Collection.exeption.EmployeeNotFoundException;
import pro.sky.springHW2.Collection.exeption.EmployeeStorageIsFullException;
import pro.sky.springHW2.Collection.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;


    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(Map.of(
                "1", new Employee("Иван", "Иванов", 1000.0, 1),
                "2", new Employee("Вася", "Пупкин", 1000.9, 1),
        "3", new Employee("Семен", "Запупыркин", 10000.50, 1),
        "4", new Employee("Пётр", "Петров", 5000.50, 2),
        "5", new Employee("Илья", "Ильин", 7000.50, 3),
        "6", new Employee("Джон", "Смит", 500.8, 3)));
    }
    private final static int MAX_SIZE = 4;

    @Override
    public Employee add(String firstName, String lastName, double salary, int departmentId) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, double salary, int departmentId) {
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
    }

    @Override
    public Employee find(String firstName, String lastName, double salary, int departmentId) {
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    @Override
    public Collection<Employee> findCollection() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
