package org.example.employeeservice.infrastructure.repository;

import org.example.employeeservice.domain.model.Employee;
import org.example.employeeservice.domain.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepository {
}
