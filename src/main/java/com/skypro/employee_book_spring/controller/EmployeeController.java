package com.skypro.employee_book_spring.controller;

import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.record.EmployeeRequest;
import com.skypro.employee_book_spring.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Stream;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public Stream<Employee> getEmployeesWithMinSalary(){
        return this.employeeService.getEmployeesWithMinSalary();
    }

    @GetMapping("/employees/salary/max")
    public Stream<Employee> getEmployeesWithMaxSalary(){
        return this.employeeService.getEmployeesWithMaxSalary();
    }

    @GetMapping("/employees/salary/moreThanAvarage")
    public Stream<Employee> getEmployeesWhoEarnMoreThanAverageSalary(){
        return this.employeeService.getEmployeesWhoEarnMoreThanAverageSalary();
    }
}
