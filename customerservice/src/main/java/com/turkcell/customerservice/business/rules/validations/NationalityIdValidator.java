package com.turkcell.customerservice.business.rules.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalityIdValidator implements ConstraintValidator<ValidNationalityId, String> {

    @Override
    public void initialize(ValidNationalityId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nationalityId, ConstraintValidatorContext context) {
        if (nationalityId == null) {
            return true;
        }
        if (!nationalityId.matches("^[1-9][0-9]{10}$")) {
            return false;
        }

        int[] digits = nationalityId.chars().map(Character::getNumericValue).toArray();

        int sumOfFirstTen = 0;
        for (int i = 0; i < 10; i++) {
            sumOfFirstTen += digits[i];
        }

        if (sumOfFirstTen % 10 != digits[10]) {
            return false;
        }

        int sumOddIndexes = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int sumEvenIndexes = digits[1] + digits[3] + digits[5] + digits[7];

        if ((7 * sumOddIndexes + 9 * sumEvenIndexes) % 10 != digits[9]) {
            return false;
        }

        if ((8 * sumOddIndexes) % 10 != digits[10]) {
            return false;
        }

        return true;
    }
}
