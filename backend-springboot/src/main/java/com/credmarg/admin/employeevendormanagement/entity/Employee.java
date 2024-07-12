package com.credmarg.admin.employeevendormanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "designation")
    private String designation;
    @Column(name = "ctc")
    private Long ctc;
    @Column(name = "email", unique = true)
    private String email;

    public Employee() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getCtc() {
        return ctc;
    }

    public void setCtc(Long ctc) {
        this.ctc = ctc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
