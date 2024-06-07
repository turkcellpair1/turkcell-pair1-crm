package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dto.requests.contact.AddContactRequest;
import com.turkcell.customerservice.business.dto.requests.contact.UpdateContactRequest;
import com.turkcell.customerservice.business.dto.responses.contact.*;

import java.util.List;

public interface ContactService {
    AddContactResponse addContact(AddContactRequest request);
    UpdateContactResponse updateContact(UpdateContactRequest request);
    String deleteByIdContact(int id);
    GetContactByIdResponse getContactById(int id);
    GetContactByCustomerIdResponse getContactByCustomerId(int id);
    List<GetAllContactsResponse> getAllContacts();
}
