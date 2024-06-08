package com.turkcell.customerservice.business.dto.responses.customer;

import com.turkcell.customerservice.business.dto.responses.account.GetAccountByCustomerIdResponse;
import com.turkcell.customerservice.business.dto.responses.address.GetAddressByIdResponse;
import com.turkcell.customerservice.business.dto.responses.address.GetAddressesByCustomerIdResponse;
import com.turkcell.customerservice.business.dto.responses.contact.GetContactByCustomerIdResponse;
import com.turkcell.customerservice.entities.Account;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerDetailByIdResponse {
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
    private List<GetAddressesByCustomerIdResponse> addresses;
    private GetContactByCustomerIdResponse contact;
    private List<GetAccountByCustomerIdResponse> accounts;
}
