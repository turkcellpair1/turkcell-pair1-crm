package com.turkcell.customerservice.core.utilities.mappers;

import com.turkcell.customerservice.business.dto.requests.contact.AddContactRequest;
import com.turkcell.customerservice.business.dto.requests.contact.UpdateContactRequest;
import com.turkcell.customerservice.business.dto.responses.contact.*;
import com.turkcell.customerservice.entities.Contact;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE= Mappers.getMapper(ContactMapper.class);

    @Mapping(source = "customerId",target = "customer.id")
    Contact addRequestToContact(AddContactRequest request);
    @Mapping(source = "customer.id",target = "customerId")
    AddContactResponse contactToAddResonse(Contact contact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact updateRequestToContact(UpdateContactRequest request);
    @Mapping(source = "customer.id",target = "customerId")
    UpdateContactResponse contactToUpdateResponse(Contact contact);

    @Mapping(source = "customer.id",target = "customerId")
    GetContactByIdResponse contactByIdToGetResponse(Contact contact);

    @Mapping(source = "customer.id",target = "customerId")
    GetContactByCustomerIdResponse contactByCustomerIdToGetResponse(Contact contact);

    @Mapping(source = "customer.id",target = "customerId")
    List<GetAllContactsResponse> contactsToGetResponse(List<Contact> contacts);
}
