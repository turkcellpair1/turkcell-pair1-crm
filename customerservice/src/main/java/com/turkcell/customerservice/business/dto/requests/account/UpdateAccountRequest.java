package com.turkcell.customerservice.business.dto.requests.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
    private int id;
    private String accountDesc;
    private int addressId;
}
