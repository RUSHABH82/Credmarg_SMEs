package com.credmarg.admin.employeevendormanagement.domain.employee;

import com.credmarg.admin.employeevendormanagement.domain.AddRequest;

public class AddEmployeeRequest extends AddRequest {

    private Long ctc;
    private String designation;

    public AddEmployeeRequest() {
        super();
    }


    public Long getCtc() {
        return ctc;
    }

    public void setCtc(Long ctc) {
        this.ctc = ctc;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
