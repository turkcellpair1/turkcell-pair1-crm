package com.turkcell.customerservice.business.dto.responses.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAddressByIdResponse {
    private int id;
    private String addressName;
    private String city;
    private String district;
    private String street;
    private String houseNumber;
    private String addressDesc;
    private boolean defaultAddress;
    private boolean status;
    private int customerId;
}
