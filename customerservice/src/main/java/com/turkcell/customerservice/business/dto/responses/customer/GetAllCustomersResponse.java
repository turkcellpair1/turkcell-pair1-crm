package com.turkcell.customerservice.business.dto.responses.customer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomersResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalityId;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String gender;
    private boolean status;
    private int user_id;
}
