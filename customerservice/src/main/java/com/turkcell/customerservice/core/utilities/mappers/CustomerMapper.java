package com.turkcell.customerservice.core.utilities.mappers;

import com.turkcell.customerservice.business.dto.requests.customer.AddCustomerRequest;
import com.turkcell.customerservice.business.dto.requests.customer.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.customer.*;
import com.turkcell.customerservice.entities.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer addRequestToCustomer(AddCustomerRequest request);
    AddCustomerResponse customerToAddResponse(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateRequestToCustomer(UpdateCustomerRequest request);
    UpdateCustomerResponse customerToUpdateResponse(Customer customer);

    GetCustomerByIdResponse customerByIdToGetResponse(Customer customer);

    GetCustomerDetailByIdResponse customerToGetDetailResponse(Customer customer);

    List<GetAllCustomersResponse> customersToGetResponse(List<Customer> customers);
}
