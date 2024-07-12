package com.credmarg.admin.employeevendormanagement.service.impl;

import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatus;
import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import com.credmarg.admin.employeevendormanagement.domain.employee.AddEmployeeRequest;
import com.credmarg.admin.employeevendormanagement.entity.Employee;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import com.credmarg.admin.employeevendormanagement.repository.EmployeeRepository;
import com.credmarg.admin.employeevendormanagement.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {


    private static final ResultStatusResponse SUCCESS_RESPONSE;

    static {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("SUCCESS");
        SUCCESS_RESPONSE = new ResultStatusResponse(resultStatus);
    }

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public ResponseEntity<ResultStatusResponse> removeEmployeeById(Integer id, String domainId) throws CredmargPortalException {
        try {
            Employee employeeToDelete = findEmployeeById(id);
            employeeRepository.delete(employeeToDelete);
            return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            throw new CredmargPortalException("fail to removeEmployeeById!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> updateEmployeeById(Integer id, AddEmployeeRequest addEmployeeRequest, String domainId) throws CredmargPortalException {
        return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResultStatusResponse> addEmployee(AddEmployeeRequest addEmployeeRequest, String domainId) throws CredmargPortalException {
        try {
            validateEmployeeAlreadyExist(addEmployeeRequest);
            Employee newEmployee = new Employee();
            newEmployee.setName(addEmployeeRequest.getName());
            newEmployee.setCtc(addEmployeeRequest.getCtc());
            newEmployee.setDesignation(addEmployeeRequest.getDesignation());
            newEmployee.setEmail(addEmployeeRequest.getEmail());
            newEmployee.setCreatedBy(domainId);
            employeeRepository.save(newEmployee);

            return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            throw new CredmargPortalException("fail to addEmployee!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployee() throws CredmargPortalException {
        try {
            return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new CredmargPortalException("fail to getAllEmployee!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Integer id) throws CredmargPortalException {
        try {
            return new ResponseEntity<>(findEmployeeById(id), HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            throw new CredmargPortalException("fail to addEmployee!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Employee findEmployeeById(Integer id) throws CredmargPortalException {
        return employeeRepository.findById(id).orElseThrow(() -> new CredmargPortalException("Invalid Employee Id!", HttpStatus.BAD_REQUEST));
    }

    private void validateEmployeeAlreadyExist(AddEmployeeRequest addEmployeeRequest) throws CredmargPortalException {
        if (employeeRepository.existsByEmail(addEmployeeRequest.getEmail())) {
            throw new CredmargPortalException("Employee already exists by given email!", HttpStatus.BAD_REQUEST);
        }
    }
}
