package org.example.employeeservice.domain.service;

import org.example.employeeservice.domain.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EmployeeService {

    Optional<Employee> findEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    void deleteEmployee(Long id);
}
