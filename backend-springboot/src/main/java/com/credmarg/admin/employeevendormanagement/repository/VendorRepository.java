package com.credmarg.admin.employeevendormanagement.repository;

import com.credmarg.admin.employeevendormanagement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    boolean existsByEmail(String email);
}
