package com.turkcell.customerservice.core.utilities.mappers;

import com.turkcell.customerservice.business.dto.responses.customer.GetCustomerByIdResponse;
import com.turkcell.customerservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    GetCustomerByIdResponse customerToGetResponse(Customer customer);
}
