package com.turkcell.customerservice.business.dto.responses.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAccountsResponse {
    private int id;
    private int accountNumber;
    private String accountStatus;
    private String accountName;
    private String accountType;
    private String accountDesc;
    private boolean status;
    private int addressId;
    private int customerId;
}
