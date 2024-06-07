package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.ContactService;
import com.turkcell.customerservice.business.dto.requests.contact.AddContactRequest;
import com.turkcell.customerservice.business.dto.requests.contact.UpdateContactRequest;
import com.turkcell.customerservice.business.dto.responses.contact.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
public class ContactController {
    private final ContactService contactService;

    @PostMapping()
    public ResponseEntity<AddContactResponse> addContact(@RequestBody @Valid AddContactRequest request){
        AddContactResponse response = contactService.addContact(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping()
    public UpdateContactResponse updateContact(@RequestBody @Valid UpdateContactRequest request){
        return contactService.updateContact(request);
    }

    /*@DeleteMapping("deleteById/{id}")
    public String deleteByIdContact(@PathVariable int id){
        return contactService.deleteByIdContact(id);
    }*/

    @GetMapping("{id}")
    public GetContactByIdResponse getContactById(@PathVariable int id){
        return contactService.getContactById(id);
    }

    @GetMapping("getContactByCustomerId/{id}")
    public GetContactByCustomerIdResponse getContactsByCustomerId(@PathVariable int id){
        return contactService.getContactByCustomerId(id);
    }

    @GetMapping("getAllContacts")
    public List<GetAllContactsResponse> getAllContacts(){
        return contactService.getAllContacts();
    }
}
