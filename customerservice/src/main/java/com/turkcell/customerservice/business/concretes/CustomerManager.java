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
    public AddCustomerResponse addCustomer(AddCustomerRequest request) {
        Customer customer=CustomerMapper.INSTANCE.addRequestToCustomer(request);
        customer.setStatus(true);
        return CustomerMapper.INSTANCE.customerToAddResponse(customerRepository.save(customer));
    }

    @Override
    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request) {
        // todo: update KESİN düzeltilmeli
        Customer customer=CustomerMapper.INSTANCE.updateRequestToCustomer(request);
        return CustomerMapper.INSTANCE.customerToUpdateResponse(customerRepository.save(customer));
    }

    @Override
    public String deleteByIdCustomer(int id) {
        // todo: işlem başarılı responsu yaz global
        // Todo: eğer müşteri yoksa hata mesajı ekle
        Customer customer=customerRepository.findById(id).orElse(null);
        customer.setStatus(false);
        customerRepository.save(customer);
        return "kullanıcı silindi";
    }


    @Override
    public GetCustomerByIdResponse getCostumerById(int request) {
        // Todo: eğer müşteri yoksa hata mesajı ekle
        return CustomerMapper.INSTANCE.customerByIdToGetResponse(customerRepository.findById(request).orElse(null));
    }

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        return CustomerMapper.INSTANCE.customersToGetResponse(customerRepository.findAll());
    }

    /*@Override
    public String deleteCustomer(DeleteCustomerRequest request) {

        CustomerMapper.INSTANCE.deleteRequestToCustomer(request);
        return "Müşteri silindi";
    }*/
}
