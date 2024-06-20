package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.*;
import com.turkcell.customerservice.business.rules.CustomerBusinessRules;
import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.core.utilities.mappers.*;
import com.turkcell.customerservice.dataAccess.AccountRepository;
import com.turkcell.customerservice.dataAccess.AddressRepository;
import com.turkcell.customerservice.dataAccess.ContactRepository;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Contact;
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
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final AccountRepository accountRepository;
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

    @Override
    public GetCustomerDetailByIdResponse getCustomerDetailById(int id) {
        GetCustomerDetailByIdResponse getCustomerDetailByIdResponse = CustomerMapper.INSTANCE.customerToGetDetailResponse(customerRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No user was found for this id.")));
        getCustomerDetailByIdResponse.setAddresses(AddressMapper.INSTANCE.addressByCustomerIdToGetResponse(addressRepository.findByCustomerIdAndStatusTrue(id)));
        getCustomerDetailByIdResponse.setContact(ContactMapper.INSTANCE.contactByCustomerIdToGetResponse(contactRepository.findByCustomerIdAndStatusTrue(id).orElse(null)));
        getCustomerDetailByIdResponse.setAccounts(AccountMapper.INSTANCE.accountByCustomerIdToGetResponse(accountRepository.findByCustomerIdAndStatusTrue(id)));
        return getCustomerDetailByIdResponse;
    }
}
