package com.turkcell.customerservice.business.dto.responses.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContactResponse {
    private int id;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
    private boolean status;
    private int customerId;
}
