package com.turkcell.customerservice.business.dto.responses.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalityId;
    private String role;
}
