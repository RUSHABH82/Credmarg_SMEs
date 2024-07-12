package com.credmarg.admin.employeevendormanagement.repository;

import com.credmarg.admin.employeevendormanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {


    Optional<Admin> findByEmail(String email);
}
