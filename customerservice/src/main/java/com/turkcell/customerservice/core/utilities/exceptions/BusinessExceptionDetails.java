package com.turkcell.customerservice.core.utilities.exceptions;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class BusinessExceptionDetails extends ExceptionDetails{
    public BusinessExceptionDetails() {
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        setTitle("Business Rule Violation");
        //setType(location+"/exceptions/business");
        setStatus("400");
    }
}
