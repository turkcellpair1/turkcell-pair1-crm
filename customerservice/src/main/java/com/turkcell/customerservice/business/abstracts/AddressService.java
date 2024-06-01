package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dto.requests.address.AddAddressRequest;
import com.turkcell.customerservice.business.dto.requests.address.UpdateAddressRequest;
import com.turkcell.customerservice.business.dto.responses.address.*;

import java.util.List;

public interface AddressService {
    AddAddressResponse addAddress(AddAddressRequest request);
    UpdateAddressResponse updateAddress(UpdateAddressRequest request);
    String updateDefaultAddressById(int id);
    String deleteByIdAddress(int id);
    GetAddressByIdResponse getAddressById(int id);
    List<GetAddressesByCustomerIdResponse> getAddressByCustomerId(int id);
    List<GetAllAddressesResponse> getAllAddresses();
}
