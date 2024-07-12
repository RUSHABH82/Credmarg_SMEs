package com.credmarg.admin.employeevendormanagement.service.impl;

import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatus;
import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import com.credmarg.admin.employeevendormanagement.domain.mail.EmailFilterRequest;
import com.credmarg.admin.employeevendormanagement.domain.mail.SendMailDetails;
import com.credmarg.admin.employeevendormanagement.domain.vendor.AddVendorRequest;
import com.credmarg.admin.employeevendormanagement.entity.Vendor;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import com.credmarg.admin.employeevendormanagement.repository.VendorRepository;
import com.credmarg.admin.employeevendormanagement.service.EmailService;
import com.credmarg.admin.employeevendormanagement.service.IVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService implements IVendorService {


    private static final ResultStatusResponse SUCCESS_RESPONSE;

    static {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("SUCCESS");
        SUCCESS_RESPONSE = new ResultStatusResponse(resultStatus);
    }

    private final VendorRepository vendorRepository;
    private final EmailService emailService;


    public VendorService(VendorRepository vendorRepository, EmailService emailService) {
        this.vendorRepository = vendorRepository;
        this.emailService = emailService;
    }


    @Override
    public ResponseEntity<ResultStatusResponse> removeVendorById(Integer id, String domainId) throws CredmargPortalException {
        try {
            Vendor vendorToDelete = findVendorById(id);
            vendorRepository.delete(vendorToDelete);
            return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            throw new CredmargPortalException("fail to removeVendorById!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> updateVendorById(Integer id, AddVendorRequest addVendorRequest, String domainId) throws CredmargPortalException {
        return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResultStatusResponse> addVendor(AddVendorRequest addVendorRequest, String domainId) throws CredmargPortalException {
        try {
            validateVendorAlreadyExist(addVendorRequest);
            Vendor newVendor = new Vendor();
            newVendor.setName(addVendorRequest.getName());
            newVendor.setUpi(addVendorRequest.getUpi());
            newVendor.setEmail(addVendorRequest.getEmail());
            vendorRepository.save(newVendor);

            return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            throw new CredmargPortalException("fail to addVendor!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<Vendor>> getAllVendor() throws CredmargPortalException {
        try {
            return new ResponseEntity<>(vendorRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new CredmargPortalException("fail to getAllVendor!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Vendor> getVendorById(Integer id) throws CredmargPortalException {
        try {
            return new ResponseEntity<>(findVendorById(id), HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            throw new CredmargPortalException("fail to addVendor!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> sendEmailsToAllVendors() throws CredmargPortalException {
        try {
            List<Vendor> vendorsList = vendorRepository.findAll();
            emailService.sendPaymentMailToVendors(vendorsList);
            return new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CredmargPortalException("fail to sendEmailsToAllVendors!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<SendMailDetails>> readAllSentEmails(EmailFilterRequest emailFilterRequest) throws CredmargPortalException {
        return new ResponseEntity<>(emailService.readSentEmails(emailFilterRequest), HttpStatus.OK);
    }


    private Vendor findVendorById(Integer id) throws CredmargPortalException {
        return vendorRepository.findById(id).orElseThrow(() -> new CredmargPortalException("Invalid Vendor Id!", HttpStatus.BAD_REQUEST));
    }

    private void validateVendorAlreadyExist(AddVendorRequest addVendorRequest) throws CredmargPortalException {
        if (vendorRepository.existsByEmail(addVendorRequest.getEmail())) {
            throw new CredmargPortalException("Vendor already exists by given email!", HttpStatus.BAD_REQUEST);
        }
    }
}
