package com.turkcell.customerservice.core.utilities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationExceptionDetails extends ExceptionDetails{
    public ValidationExceptionDetails() {
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        //setType(location+"/exceptions/validation");
        setStatus("400");
    }
    private Map<String,String> errors;
}
