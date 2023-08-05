package pro.sky.springHW2.Collection.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.sky.springHW2.Collection.model.Employee;
import pro.sky.springHW2.Collection.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return service.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return service.remove(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return service.find(firstName, lastName, salary, departmentId);
    }

    @GetMapping
    public Collection<Employee> findCollecton() {
        return service.findCollection();
    }

}
