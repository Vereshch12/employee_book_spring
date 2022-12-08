package com.skypro.employee_book_spring.service;

import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        if (employeeRequest.getName() == null || employeeRequest.getLastname() == null){
            throw new IllegalArgumentException("У сотрудника нет имени или фамилии!");
        }
        Employee employee = new Employee(employeeRequest.getName(),
                employeeRequest.getLastname(),
                employeeRequest.getDepartament(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum(){
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    public Stream<Employee> getEmployeesWithMinSalary(){
        int minSalary = employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return employees.values().stream()
                .filter(e -> e.getSalary() == minSalary);
    }

    public Stream<Employee> getEmployeesWithMaxSalary(){
        int maxSalary = employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        return employees.values().stream()
                .filter(e -> e.getSalary() == maxSalary);
    }

    public Stream<Employee> getEmployeesWhoEarnMoreThanAverageSalary(){
        int averageSalary = (int) ((employees.values().stream()
                        .mapToInt(e -> e.getSalary())
                        .sum()) / (employees.values().stream()
                        .count()));
        return employees.values().stream()
                .filter(e -> e.getSalary() > averageSalary);
    }
}
