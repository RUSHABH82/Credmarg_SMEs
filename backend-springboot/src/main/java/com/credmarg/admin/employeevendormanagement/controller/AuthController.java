package com.credmarg.admin.employeevendormanagement.controller;

import com.credmarg.admin.employeevendormanagement.domain.auth.LoginRequest;
import com.credmarg.admin.employeevendormanagement.domain.auth.LoginResponse;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import com.credmarg.admin.employeevendormanagement.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest loginRequest) throws CredmargPortalException {
        return authService.adminLogin(loginRequest);
    }
}
