package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetAllCustomersResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.business.dto.responses.customer.UpdateCustomerResponse;

import java.util.List;

public interface CustomerService {
    AddCustomerResponse addCustomer(AddCustomerRequest request);
    UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request);
    String deleteByIdCustomer(int id);
    GetCustomerByIdResponse getCostumerById(int id);
    List<GetAllCustomersResponse> getAllCustomers();
}
