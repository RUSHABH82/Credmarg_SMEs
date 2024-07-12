package com.credmarg.admin.employeevendormanagement.repository;

import com.credmarg.admin.employeevendormanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);
}
