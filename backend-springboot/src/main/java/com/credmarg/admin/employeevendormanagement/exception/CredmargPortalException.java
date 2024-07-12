package com.credmarg.admin.employeevendormanagement.exception;

import org.springframework.http.HttpStatus;

public class CredmargPortalException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;


    /**
     * @param message
     */
    public CredmargPortalException(String message) {
        super(message);
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CredmargPortalException(String messg, HttpStatus httpStatus) {
        super(messg);
        this.httpStatus = httpStatus;
        this.message = messg;
    }

    public CredmargPortalException(String msg, Throwable cause) {
        super(msg, cause);
        this.message = msg;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    }

    public CredmargPortalException(Throwable cause) {
        super(cause);
        this.message = "";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
