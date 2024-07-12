package com.credmarg.admin.employeevendormanagement.service;

import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import com.credmarg.admin.employeevendormanagement.domain.employee.AddEmployeeRequest;
import com.credmarg.admin.employeevendormanagement.entity.Employee;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeService {

    ResponseEntity<ResultStatusResponse> removeEmployeeById(Integer id, String domainId) throws CredmargPortalException;

    ResponseEntity<ResultStatusResponse> updateEmployeeById(Integer id, AddEmployeeRequest addEmployeeRequest, String domainId)
            throws CredmargPortalException;

    ResponseEntity<ResultStatusResponse> addEmployee(AddEmployeeRequest addEmployeeRequest, String domainId)
            throws CredmargPortalException;


    ResponseEntity<List<Employee>> getAllEmployee() throws CredmargPortalException;

    ResponseEntity<Employee> getEmployeeById(Integer id) throws CredmargPortalException;
}
