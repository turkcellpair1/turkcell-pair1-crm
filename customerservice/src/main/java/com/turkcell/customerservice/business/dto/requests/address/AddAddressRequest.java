package com.turkcell.customerservice.business.dto.requests.address;

import com.turkcell.customerservice.entities.Customer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressRequest {
    @NotBlank(message = "Address name cannot be null")
    private String addressName;
    @NotBlank(message = "City name cannot be null")
    private String city;
    @NotBlank(message = "Disrict name cannot be null")
    private String district;
    @NotBlank(message = "Street name cannot be null")
    private String street;
    @NotBlank(message = "House number cannot be null")
    private String houseNumber;
    @NotBlank(message = "Address description cannot be null")
    private String addressDesc;
    private int customerId;
}
