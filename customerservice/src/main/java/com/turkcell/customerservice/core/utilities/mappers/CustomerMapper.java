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

    //AddCustomer
    Customer addRequestToCustomer(AddCustomerRequest request);
//    @Mapping(source = "user_id",target = "user_id")
//    @Mapping(source = "status",target = "status")
    AddCustomerResponse customerToAddResponse(Customer customer);

    //UpdateCustomer
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateRequestToCustomer(UpdateCustomerRequest request);
    UpdateCustomerResponse customerToUpdateResponse(Customer customer);


    //GetByID
//    @Mapping(source = "user_id",target = "user_id")
//    @Mapping(source = "status",target = "status")
    GetCustomerByIdResponse customerByIdToGetResponse(Customer customer);

    //GetDetailById
//    @Mapping(source = "user_id",target = "user_id")
//    @Mapping(source = "status",target = "status")
    GetCustomerDetailByIdResponse customerToGetDetailResponse(Customer customer);

    //GetAll
//    @Mapping(source = "user_id",target = "user_id")
//    @Mapping(source = "status",target = "status")
    List<GetAllCustomersResponse> customersToGetResponse(List<Customer> customers);
}
