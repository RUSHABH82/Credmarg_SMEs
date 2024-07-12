package com.credmarg.admin.employeevendormanagement.service.impl;

import com.credmarg.admin.employeevendormanagement.domain.auth.LoginRequest;
import com.credmarg.admin.employeevendormanagement.domain.auth.LoginResponse;
import com.credmarg.admin.employeevendormanagement.domain.jwt.JwtDataPayload;
import com.credmarg.admin.employeevendormanagement.entity.Admin;
import com.credmarg.admin.employeevendormanagement.exception.CredmargPortalException;
import com.credmarg.admin.employeevendormanagement.repository.AdminRepository;
import com.credmarg.admin.employeevendormanagement.service.IAuthService;
import com.credmarg.admin.employeevendormanagement.utils.EncryptionUtils;
import com.credmarg.admin.employeevendormanagement.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.credmarg.admin.employeevendormanagement.constant.EmpVendorConst.CLS_MET_ERROR;

@Service
public class AuthService implements IAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    private final AdminRepository adminRepository;
    private final String jwtSecret;

    public AuthService(AdminRepository adminRepository, @Value("${application.jwt.secretKey}") String jwtSecret) {
        this.adminRepository = adminRepository;
        this.jwtSecret = jwtSecret;
    }

    @Override
    public ResponseEntity<LoginResponse> adminLogin(LoginRequest loginRequest) throws CredmargPortalException {
        try {
            Admin admin = getAdminByEmail(loginRequest.getUsername());
            if (!EncryptionUtils.planTextToHex(loginRequest.getPassword()).equals(admin.getPassword())) {
                throw new CredmargPortalException("Invalid Username password!, Try again.", HttpStatus.NOT_FOUND);
            }
            JwtDataPayload dataPayload = new JwtDataPayload();
            dataPayload.setEmail(admin.getEmail());
            dataPayload.setName(admin.getName());
            dataPayload.setRole("ADMIN");

            ObjectMapper oMapper = new ObjectMapper();
            Map<String, Object> map = oMapper.convertValue(dataPayload, Map.class);
            String token = JwtTokenUtil.getJwtToken(map, jwtSecret);

            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
        } catch (CredmargPortalException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), "adminLogin", e.getMessage());
            throw new CredmargPortalException("Invalid Username!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Admin getAdminByEmail(String email) throws CredmargPortalException {
        return adminRepository.findByEmail(email).orElseThrow(() -> new CredmargPortalException("Invalid Username!", HttpStatus.NOT_FOUND));
    }

}
