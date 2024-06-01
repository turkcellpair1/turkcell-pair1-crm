package com.turkcell.customerservice.business.dto.requests.address;

import com.turkcell.customerservice.entities.Customer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {
    //@NotBlank(message = "ID cannot be null")
    private int id;
    private String addressName;
    private String city;
    private String district;
    private String street;
    private String houseNumber;
    private String addressDesc;
    //private int customerId;
}
