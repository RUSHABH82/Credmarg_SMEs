package com.credmarg.admin.employeevendormanagement.service;

import com.credmarg.admin.employeevendormanagement.domain.auth.LoginRequest;
import com.credmarg.admin.employeevendormanagement.domain.auth.LoginResponse;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import org.springframework.http.ResponseEntity;

public interface IAuthService {


    ResponseEntity<LoginResponse> adminLogin(LoginRequest loginRequest) throws CredmargPortalException;

}
