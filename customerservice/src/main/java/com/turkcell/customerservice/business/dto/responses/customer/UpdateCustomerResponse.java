package com.turkcell.customerservice.business.dto.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalityId;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String gender;
    private int user_id;
}
