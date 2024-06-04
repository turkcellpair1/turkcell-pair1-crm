package com.turkcell.customerservice.core.utilities.mappers;

import com.turkcell.customerservice.business.dto.requests.account.AddAccountRequest;
import com.turkcell.customerservice.business.dto.requests.account.UpdateAccountRequest;
import com.turkcell.customerservice.business.dto.responses.account.*;
import com.turkcell.customerservice.entities.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE= Mappers.getMapper(AccountMapper.class);

    //AddAccount
    @Mapping(source = "customerId", target="customer.id")
    @Mapping(source = "addressId", target="address.id")
    Account addRequestToAccount(AddAccountRequest request);
    @Mapping(source = "customer.id", target="customerId")
    @Mapping(source = "address.id", target="addressId")
    AddAccountResponse accountToAddResponse(Account account);

    //UpdateAccount
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account updateRequestToAccount(UpdateAccountRequest request);
    @Mapping(source = "customer.id", target="customerId")
    @Mapping(source = "address.id", target="addressId")
    UpdateAccountResponse accountToUpdateResponse(Account account);

    //GetById
    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(source = "address.id", target="addressId")
    GetAccountByIdResponse accountByIdToGetResponse(Account account);

    //GetByCustomerId
    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(source = "address.id", target="addressId")
    List<GetAccountByCustomerIdResponse> accountByCustomerIdToGetResponse(List<Account> accounts);

    //GetAll
    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(source = "address.id", target="addressId")
    List<GetAllAccountsResponse> accountsToGetResponse(List<Account> accounts);
}
