package com.turkcell.customerservice.business.dto.requests.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountRequest {
    @NotBlank(message = "Account description cannot be null")
    private String accountDesc;
    private int addressId;
    private int customerId;
}
