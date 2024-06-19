package com.turkcell.authserver.core.exceptions;

public class BusinessExceptionDetails extends ExceptionDetails {
    public BusinessExceptionDetails() {
        setTitle("Business Rule Violation");
        setType("http://crm.com/exceptions/business");
        setStatus("400");
    }
}
