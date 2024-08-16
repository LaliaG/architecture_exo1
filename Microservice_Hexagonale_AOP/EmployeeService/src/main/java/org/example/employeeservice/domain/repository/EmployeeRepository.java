package org.example.employeeservice.domain.repository;

import org.example.employeeservice.domain.model.Employee;

import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
}
