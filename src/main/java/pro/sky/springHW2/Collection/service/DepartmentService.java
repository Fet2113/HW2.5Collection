package pro.sky.springHW2.Collection.service;

import org.springframework.stereotype.Service;
import pro.sky.springHW2.Collection.exeption.EmployeeNotFoundException;
import pro.sky.springHW2.Collection.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(Integer departmentId) {


        return employeeService.findCollection().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
    }

    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.findCollection().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartment(Integer departmentId) {
        return employeeService.findCollection().stream()
                .filter(e -> departmentId == null || e.getDepartmentId() == departmentId)
                .collect(groupingBy(Employee::getDepartmentId, toList()));
    }
}
