package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetAllCustomersResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.business.dto.responses.customer.UpdateCustomerResponse;
import com.turkcell.customerservice.business.rules.CustomerBusinessRules;
import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.core.utilities.mappers.CustomerMapper;
import com.turkcell.customerservice.core.utilities.mappers.NullAwareBeanUtils;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public AddCustomerResponse addCustomer(AddCustomerRequest request) {
        customerBusinessRules.checkIfNationalityIdExist(request.getNationalityId());
        customerBusinessRules.checkIfUserIdExist(request.getUser_id());
        Customer customer=CustomerMapper.INSTANCE.addRequestToCustomer(request);
        customer.setStatus(true);
        return CustomerMapper.INSTANCE.customerToAddResponse(customerRepository.save(customer));
    }

    @Override
    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request) {
        customerBusinessRules.checkIfNationalityIdExist(request.getNationalityId());
        //customerBusinessRules.checkIfUserIdExist(request.getUser_id());
        return customerRepository.findByIdAndStatusTrue(request.getId()).map(customer -> {
            NullAwareBeanUtils.copyNonNullProperties(request,customer);
            return CustomerMapper.INSTANCE.customerToUpdateResponse(customerRepository.save(customer));
                }).orElseThrow(() -> new BusinessException("No user was found for this id. Transaction failed."));
    }

    @Override
    public String deleteByIdCustomer(int id) {
        Customer customer=customerRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No user was found for this id. Transaction failed."));
        customer.setStatus(false);
        customerRepository.save(customer);
        return "User deleted.";
    }


    @Override
    public GetCustomerByIdResponse getCostumerById(int request) {
        return CustomerMapper.INSTANCE.customerByIdToGetResponse(customerRepository.findByIdAndStatusTrue(request).orElseThrow(() -> new BusinessException("No user was found for this id.")));
    }

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        return CustomerMapper.INSTANCE.customersToGetResponse(customerRepository.findByStatusTrue());
    }
}
