package com.textile.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TenantMismatchException extends RuntimeException {

    public TenantMismatchException() {
        super("Access denied: resource does not belong to your tenant");
    }

    public TenantMismatchException(String message) {
        super(message);
    }
}