package com.credmarg.admin.employeevendormanagement.controller;


import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import com.credmarg.admin.employeevendormanagement.domain.employee.AddEmployeeRequest;
import com.credmarg.admin.employeevendormanagement.entity.Employee;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import com.credmarg.admin.employeevendormanagement.service.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final IEmployeeService employeeService;


    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @DeleteMapping("{id}")
    ResponseEntity<ResultStatusResponse> removeEmployeeById(@PathVariable Integer id, String domainId) throws CredmargPortalException {
        return employeeService.removeEmployeeById(id, "domainId");
    }

    @PatchMapping("{id}")
    ResponseEntity<ResultStatusResponse> updateEmployeeById(@PathVariable Integer id, @RequestBody AddEmployeeRequest addEmployeeRequest, String domainId) throws CredmargPortalException {
        return employeeService.updateEmployeeById(id, addEmployeeRequest, "domainId");
    }

    @PostMapping
    ResponseEntity<ResultStatusResponse> addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest, String domainId) throws CredmargPortalException {
        return employeeService.addEmployee(addEmployeeRequest, "domainId");
    }


    @GetMapping
    ResponseEntity<List<Employee>> getAllEmployee() throws CredmargPortalException {
        return employeeService.getAllEmployee();
    }

    @GetMapping("{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) throws CredmargPortalException {
        return employeeService.getEmployeeById(id);
    }


}
