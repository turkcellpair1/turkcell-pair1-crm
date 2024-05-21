package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetAllCustomersResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.business.dto.responses.customer.UpdateCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public GetCustomerByIdResponse getCustomerById(@PathVariable int id){
        return customerService.getCostumerById(id);
    }

    @GetMapping("getAll")
    public List<GetAllCustomersResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping()
    public AddCustomerResponse addCustomer(@RequestBody AddCustomerRequest request){
        return customerService.addCustomer(request);
    }

    @PutMapping()
    public UpdateCustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest request){
        return customerService.updateCustomer(request);
    }

}
