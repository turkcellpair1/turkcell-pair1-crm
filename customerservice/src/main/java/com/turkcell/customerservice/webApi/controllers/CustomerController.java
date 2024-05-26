package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetAllCustomersResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.business.dto.responses.customer.UpdateCustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<AddCustomerResponse> addCustomer(@RequestBody @Valid AddCustomerRequest request){
        AddCustomerResponse response = customerService.addCustomer(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping()
    public UpdateCustomerResponse updateCustomer(@RequestBody @Valid UpdateCustomerRequest request){
        return customerService.updateCustomer(request);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteByIdCustomer(@PathVariable int id){
        return customerService.deleteByIdCustomer(id);
    }

    @GetMapping("{id}")
    public GetCustomerByIdResponse getCustomerById(@PathVariable int id){
        return customerService.getCostumerById(id);
    }

    @GetMapping("getAllCustomers")
    public List<GetAllCustomersResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
