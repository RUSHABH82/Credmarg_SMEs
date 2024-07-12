package com.credmarg.admin.employeevendormanagement.controller;

import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import com.credmarg.admin.employeevendormanagement.domain.mail.EmailFilterRequest;
import com.credmarg.admin.employeevendormanagement.domain.mail.SendMailDetails;
import com.credmarg.admin.employeevendormanagement.domain.vendor.AddVendorRequest;
import com.credmarg.admin.employeevendormanagement.entity.Vendor;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import com.credmarg.admin.employeevendormanagement.service.IVendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendor")
public class VendorController {

    private final IVendorService vendorService;


    public VendorController(IVendorService vendorService) {
        this.vendorService = vendorService;
    }


    @DeleteMapping("{id}")
    ResponseEntity<ResultStatusResponse> removeVendorById(@PathVariable Integer id, String domainId) throws CredmargPortalException {
        return vendorService.removeVendorById(id, "domainId");
    }

    @PatchMapping("{id}")
    ResponseEntity<ResultStatusResponse> updateVendorById(@PathVariable Integer id, @RequestBody AddVendorRequest addVendorRequest, String domainId) throws CredmargPortalException {
        return vendorService.updateVendorById(id, addVendorRequest, "domainId");
    }

    @PostMapping
    ResponseEntity<ResultStatusResponse> addVendor(@RequestBody AddVendorRequest addVendorRequest, String domainId) throws CredmargPortalException {
        return vendorService.addVendor(addVendorRequest, "domainId");
    }


    @GetMapping
    ResponseEntity<List<Vendor>> getAllVendor() throws CredmargPortalException {
        return vendorService.getAllVendor();
    }

    @GetMapping("{id}")
    ResponseEntity<Vendor> getVendorById(@PathVariable Integer id) throws CredmargPortalException {
        return vendorService.getVendorById(id);
    }


    @PostMapping("mail/send")
    ResponseEntity<ResultStatusResponse> sendEmailsToAllVendors() throws CredmargPortalException {
        return vendorService.sendEmailsToAllVendors();
    }

    @PostMapping("mail/read")
    ResponseEntity<List<SendMailDetails>> readAllSentEmails(@RequestBody EmailFilterRequest filter) throws CredmargPortalException {
        return vendorService.readAllSentEmails(filter);
    }


}
