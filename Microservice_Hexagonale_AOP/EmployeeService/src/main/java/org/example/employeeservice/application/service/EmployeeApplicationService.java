package org.example.employeeservice.application.service;

import org.example.employeeservice.domain.model.Employee;
import org.example.employeeservice.domain.repository.EmployeeRepository;
import org.example.employeeservice.domain.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeApplicationService implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeApplicationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
