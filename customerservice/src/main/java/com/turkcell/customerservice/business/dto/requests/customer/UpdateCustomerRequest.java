package com.turkcell.customerservice.business.dto.requests.customer;

import com.turkcell.customerservice.business.rules.validations.ValidBirthDate;
import com.turkcell.customerservice.business.rules.validations.ValidNationalityId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private int id;
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$",
            message = "First name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")
    private String firstName;
    @Size(max = 50, message = "Middle name must be max 50 characters")
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$", message = "Middle name must contain only letters or both letters and numbers")
    private String middleName;
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    /*@Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9]+$",
            message = "Last name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")*/
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$",
            message = "Last name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")
    private String lastName;
    @ValidNationalityId
    private String nationalityId;
    @ValidBirthDate
    private LocalDate birthDate;
    @Size(max = 50, message = "Mother name must be max 50 characters")
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$",
            message = "Mother name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")
    private String motherName;
    @Size(max = 50, message = "Father name must be max 50 characters")
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$",
            message = "Father name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")
    private String fatherName;
    @Pattern(regexp = "(?i)male|female", message = "Gender must be either 'Male' or 'Female'")
    private String gender;
    //private int user_id;
}
