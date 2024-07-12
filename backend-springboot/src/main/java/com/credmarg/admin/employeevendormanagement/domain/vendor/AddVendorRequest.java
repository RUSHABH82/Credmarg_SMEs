package com.credmarg.admin.employeevendormanagement.domain.vendor;

import com.credmarg.admin.employeevendormanagement.domain.AddRequest;

public class AddVendorRequest extends AddRequest {


    private String upi;

    public AddVendorRequest() {
        super();
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }
}
