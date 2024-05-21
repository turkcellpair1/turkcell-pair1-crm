package com.turkcell.customerservice.core.utilities.mappers;

import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetAllCustomersResponse;
import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.business.dto.responses.customer.UpdateCustomerResponse;
import com.turkcell.customerservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    //GetByID
    GetCustomerByIdResponse customerByIdToGetResponse(Customer customer);

    //GetAll
    @Mapping(source = "user_id",target = "user_id")
    List<GetAllCustomersResponse> customersToGetResponse(List<Customer> customers);

    //AddCustomer
    Customer addRequestToCustomer(AddCustomerRequest request);
    AddCustomerResponse customerToAddResponse(Customer customer);

    //UpdateCustomer
    Customer updateRequestToCustomer(UpdateCustomerRequest request);
    UpdateCustomerResponse customerToUpdateResponse(Customer customer);
}
