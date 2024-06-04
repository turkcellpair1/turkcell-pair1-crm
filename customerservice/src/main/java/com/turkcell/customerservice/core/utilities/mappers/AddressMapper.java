package com.turkcell.customerservice.core.utilities.mappers;

import com.turkcell.customerservice.business.dto.requests.address.AddAddressRequest;
import com.turkcell.customerservice.business.dto.requests.address.UpdateAddressRequest;
import com.turkcell.customerservice.business.dto.responses.address.*;
import com.turkcell.customerservice.entities.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);

    //AddAddress
    @Mapping(source = "customerId",target = "customer.id")
    Address addRequestToAddress(AddAddressRequest request);
    @Mapping(source = "customer.id",target = "customerId")
    AddAddressResponse addressToAddResonse(Address address);

    //UpdateAddress
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateRequestToAddress(UpdateAddressRequest request);
    @Mapping(source = "customer.id",target = "customerId")
    UpdateAddressResponse addressToUpdateResponse(Address address);

    //GetById
    @Mapping(source = "customer.id",target = "customerId")
    GetAddressByIdResponse addressByIdToGetResponse(Address address);

    //GetByCustomerId
    @Mapping(source = "customer.id",target = "customerId")
    List<GetAddressesByCustomerIdResponse> addressByCustomerIdToGetResponse(List<Address> addresses);

    //GetAll
    @Mapping(source = "customer.id",target = "customerId")
    List<GetAllAddressesResponse> addressesToGetResponse(List<Address> addresses);
}
