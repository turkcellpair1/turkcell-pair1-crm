package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.GetCustomerByIdRequest;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public GetCustomerByIdResponse getCustomerById(@PathVariable int id){
        return customerService.getCostumerById(id);
    }

}
