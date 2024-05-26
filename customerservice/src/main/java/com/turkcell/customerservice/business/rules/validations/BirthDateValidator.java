package com.turkcell.customerservice.business.rules.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthDateValidator implements ConstraintValidator<ValidBirthDate, LocalDate> {

    @Override
    public void initialize(ValidBirthDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false;
        }

        LocalDate now = LocalDate.now();

        if (birthDate.isAfter(now)) {
            return false;
        }

        int day = birthDate.getDayOfMonth();
        int month = birthDate.getMonthValue();
        int year = birthDate.getYear();

        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        return true;
    }
}
