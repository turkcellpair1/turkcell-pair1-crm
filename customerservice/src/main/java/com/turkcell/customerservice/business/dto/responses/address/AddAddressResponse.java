package com.turkcell.customerservice.business.dto.responses.address;

import com.turkcell.customerservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressResponse {
    private int id;
    private String addressName;
    private String city;
    private boolean defaultAddress;
    private int customerId;

}
