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
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setStatus("400");
    }
    private Map<String,String> errors;
}
