package com.credmarg.admin.employeevendormanagement.exception;


import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatus;
import com.credmarg.admin.employeevendormanagement.domain.commonresponse.ResultStatusResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionControllerAdvice {

    public ExceptionControllerAdvice() {
        super();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ex.printStackTrace();
        ResultStatus status = new ResultStatus();
        status.setStatus("FAILED");
        status.setErrorCode("Technical Difficulty!");
        status.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(new ResultStatusResponse(status), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CredmargPortalException.class)
    public ResponseEntity<Object> handleRestException(CredmargPortalException ex) {
        ResultStatus status = new ResultStatus();
        status.setStatus("FAILED");
        status.setErrorCode(Integer.toString(ex.getHttpStatus().value()));
        status.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(new ResultStatusResponse(status), ex.getHttpStatus());
    }


}
