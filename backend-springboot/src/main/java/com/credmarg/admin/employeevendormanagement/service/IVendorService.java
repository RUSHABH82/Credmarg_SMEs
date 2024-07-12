package com.credmarg.admin.employeevendormanagement.service;

import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import com.credmarg.admin.employeevendormanagement.domain.mail.EmailFilterRequest;
import com.credmarg.admin.employeevendormanagement.domain.mail.SendMailDetails;
import com.credmarg.admin.employeevendormanagement.domain.vendor.AddVendorRequest;
import com.credmarg.admin.employeevendormanagement.entity.Vendor;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IVendorService {

    ResponseEntity<ResultStatusResponse> removeVendorById(Integer id, String domainId) throws CredmargPortalException;

    ResponseEntity<ResultStatusResponse> updateVendorById(Integer id, AddVendorRequest addVendorRequest, String domainId) throws CredmargPortalException;

    ResponseEntity<ResultStatusResponse> addVendor(AddVendorRequest addVendorRequest, String domainId) throws CredmargPortalException;


    ResponseEntity<List<Vendor>> getAllVendor() throws CredmargPortalException;

    ResponseEntity<Vendor> getVendorById(Integer id) throws CredmargPortalException;

    ResponseEntity<ResultStatusResponse> sendEmailsToAllVendors() throws CredmargPortalException;

    ResponseEntity<List<SendMailDetails>> readAllSentEmails(EmailFilterRequest emailFilterRequest) throws CredmargPortalException;

}
