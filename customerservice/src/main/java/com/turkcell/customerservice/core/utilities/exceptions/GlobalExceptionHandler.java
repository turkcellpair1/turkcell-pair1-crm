package com.turkcell.customerservice.core.utilities.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessExceptionDetails handleRunTimeException(BusinessException exception){

        BusinessExceptionDetails businessProblemDetails = new BusinessExceptionDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDetails handleValidationException(MethodArgumentNotValidException exception){

        Map<String,String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().stream().map(error -> validationErrors.put(error.getField(),error.getDefaultMessage())).toList();
        ValidationExceptionDetails validationProblemDetails = new ValidationExceptionDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDateTimeParseException(HttpMessageNotReadableException ex){
        Throwable rootCause = ex.getCause();
        if (rootCause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) rootCause;
            if (ife.getTargetType().isAssignableFrom(LocalDate.class)) {
                Throwable dateTimeParseExceptionCause = ife.getCause();
                if (dateTimeParseExceptionCause instanceof DateTimeParseException) {
                    return "Failed to deserialize. The sent request must be in ‘yyyy-mm-dd’ format.";
                }
            }
        }
        return "Failed to deserialize. The sent request must be in the correct format. Please check.";
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception){
        return "Hata mesajımız şu şekilde: "+exception.getMessage();
    }
}