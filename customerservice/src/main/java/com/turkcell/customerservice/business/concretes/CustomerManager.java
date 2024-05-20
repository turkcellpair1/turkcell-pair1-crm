package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.GetCustomerByIdRequest;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.core.utilities.mappers.CustomerMapper;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public GetCustomerByIdResponse getCostumerById(int request) {
        // Todo: eğer müşteri yoksa hata mesajı ekle
        return CustomerMapper.INSTANCE.customerToGetResponse(customerRepository.findById(request).orElse(null));
    }
}
