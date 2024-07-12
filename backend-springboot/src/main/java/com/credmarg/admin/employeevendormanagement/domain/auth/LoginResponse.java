package com.credmarg.admin.employeevendormanagement.domain.auth;

public class LoginResponse {

    private String token;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
