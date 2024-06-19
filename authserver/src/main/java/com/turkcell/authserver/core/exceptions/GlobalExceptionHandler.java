package com.turkcell.authserver.core.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessExceptionDetails handleBusinessException(BusinessException businessException) {
        BusinessExceptionDetails businessExceptionDetails = new BusinessExceptionDetails();
        businessExceptionDetails.setDetail(businessException.getMessage());
        return businessExceptionDetails;
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BusinessExceptionDetails handleNoSuchElementException(NoSuchElementException exception) {
        BusinessExceptionDetails businessExceptionDetails = new BusinessExceptionDetails();
        businessExceptionDetails.setDetail(exception.getMessage());
        businessExceptionDetails.setStatus("500");
        return businessExceptionDetails;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BusinessExceptionDetails handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        BusinessExceptionDetails businessExceptionDetails = new BusinessExceptionDetails();
        businessExceptionDetails.setDetail(extractEmailFromMessage(exception.getMessage()));
        businessExceptionDetails.setStatus("500");
        return businessExceptionDetails;
    }
    private String extractEmailFromMessage(String message) {
        String emailPattern = "Key \\(email\\)=\\(([^)]+)\\)";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
