package com.turkcell.customerservice.business.dto.requests.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactRequest {
    private int id;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{11}$", message = "Mobile phone must be exactly 11 digits")
    private String mobilePhone;
    @Pattern(regexp = "^\\d{11}$", message = "Home phone must be exactly 11 digits")
    private String homePhone;
    @Pattern(regexp = "^(\\d{2}-\\d{3}-\\d{7})?$", message = "Fax must be in the format 'CountryCode-AreaCode-FaxNumber', e.g., 44-112-7859642")
    private String fax;
}
