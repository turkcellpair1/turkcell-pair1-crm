package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetAllCustomersResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.business.dto.responses.customer.UpdateCustomerResponse;
import com.turkcell.customerservice.core.utilities.mappers.CustomerMapper;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public GetCustomerByIdResponse getCostumerById(int request) {
        // Todo: eğer müşteri yoksa hata mesajı ekle
        return CustomerMapper.INSTANCE.customerByIdToGetResponse(customerRepository.findById(request).orElse(null));
    }

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        return CustomerMapper.INSTANCE.customersToGetResponse(customerRepository.findAll());
    }

    @Override
    public AddCustomerResponse addCustomer(AddCustomerRequest request) {
        Customer customer=CustomerMapper.INSTANCE.addRequestToCustomer(request);
        return CustomerMapper.INSTANCE.customerToAddResponse(customerRepository.save(customer));
    }

    @Override
    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request) {
        Customer customer=CustomerMapper.INSTANCE.updateRequestToCustomer(request);
        return CustomerMapper.INSTANCE.customerToUpdateResponse(customerRepository.save(customer));
    }
}
