package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dto.requests.customer.GetCustomerByIdRequest;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;

public interface CustomerService {
    GetCustomerByIdResponse getCostumerById(int request);

}
