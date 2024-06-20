package com.turkcell.customerservice.core.utilities.exceptions;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class BusinessExceptionDetails extends ExceptionDetails{
    public BusinessExceptionDetails() {
        setTitle("Business Rule Violation");
        setStatus("400");
    }
}
