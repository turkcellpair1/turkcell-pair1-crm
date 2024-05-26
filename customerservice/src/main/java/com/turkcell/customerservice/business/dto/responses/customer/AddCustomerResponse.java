package com.turkcell.customerservice.business.dto.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private int user_id;
    private boolean status;
}
