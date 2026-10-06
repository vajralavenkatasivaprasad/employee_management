package com.example.employee_management.controller;

import com.example.employee_management.dto.DashboardStats;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final EmployeeRepository employeeRepository;

    public DashboardController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/stats")
    public DashboardStats getStats() {
        Double highestSalary = employeeRepository.findMaxSalary();
        Double lowestSalary = employeeRepository.findMinSalary();

        return new DashboardStats(
                employeeRepository.count(),
                employeeRepository.countByDepartmentIgnoreCase("IT"),
                employeeRepository.countByDepartmentIgnoreCase("HR"),
                employeeRepository.countByDepartmentIgnoreCase("Finance"),
                employeeRepository.countByDepartmentIgnoreCase("Sales"),
                highestSalary == null ? 0.0 : highestSalary,
                lowestSalary == null ? 0.0 : lowestSalary
        );
    }
}
