package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.ContactService;
import com.turkcell.customerservice.business.dto.requests.contact.AddContactRequest;
import com.turkcell.customerservice.business.dto.requests.contact.UpdateContactRequest;
import com.turkcell.customerservice.business.dto.responses.contact.*;
import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.core.utilities.mappers.ContactMapper;
import com.turkcell.customerservice.core.utilities.mappers.NullAwareBeanUtils;
import com.turkcell.customerservice.dataAccess.ContactRepository;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactManager implements ContactService {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;


    @Override
    public AddContactResponse addContact(AddContactRequest request) {
        customerRepository.findByIdAndStatusTrue(request.getCustomerId()).orElseThrow(() -> new BusinessException("No customer was found for this id."));
        Contact contact = ContactMapper.INSTANCE.addRequestToContact(request);
        contact.setStatus(true);
        return ContactMapper.INSTANCE.contactToAddResonse(contactRepository.save(contact));
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest request) {
        return contactRepository.findByIdAndStatusTrue(request.getId()).map(contact -> {
            NullAwareBeanUtils.copyNonNullProperties(request,contact);
            return ContactMapper.INSTANCE.contactToUpdateResponse(contactRepository.save(contact));}).orElseThrow(() -> new BusinessException("No contact was found for this id. Transaction failed."));
    }

    @Override
    public String deleteByIdContact(int id) {
        Contact contact = contactRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No contact was found for this id."));
        contact.setStatus(false);
        contactRepository.save(contact);
        return "Contact deleted.";
    }

    @Override
    public GetContactByIdResponse getContactById(int id) {
        return ContactMapper.INSTANCE.contactByIdToGetResponse(contactRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No contact was found for this id.")));
    }

    @Override
    public GetContactByCustomerIdResponse getContactByCustomerId(int id) {
        return ContactMapper.INSTANCE.contactByCustomerIdToGetResponse(contactRepository.findByCustomerIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No contact was found for this customer id.")));
    }

    @Override
    public List<GetAllContactsResponse> getAllContacts() {
        return ContactMapper.INSTANCE.contactsToGetResponse(contactRepository.findByStatusTrue());
    }
}
