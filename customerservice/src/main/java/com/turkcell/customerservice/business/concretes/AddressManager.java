package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.AddressService;
import com.turkcell.customerservice.business.dto.requests.address.AddAddressRequest;
import com.turkcell.customerservice.business.dto.requests.address.UpdateAddressRequest;
import com.turkcell.customerservice.business.dto.responses.address.*;
import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.core.utilities.mappers.AddressMapper;
import com.turkcell.customerservice.core.utilities.mappers.NullAwareBeanUtils;
import com.turkcell.customerservice.dataAccess.AddressRepository;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AddAddressResponse addAddress(AddAddressRequest request) {
        customerRepository.findByIdAndStatusTrue(request.getCustomerId()).orElseThrow(() -> new BusinessException("No customer was found for this id."));
        Address address= AddressMapper.INSTANCE.addRequestToAddress(request);
        address.setStatus(true);
        return AddressMapper.INSTANCE.addressToAddResonse(addressRepository.save(address));
    }

    @Override
    public UpdateAddressResponse updateAddress(UpdateAddressRequest request) {
        return addressRepository.findByIdAndStatusTrue(request.getId()).map(address -> {
            NullAwareBeanUtils.copyNonNullProperties(request,address);
            return AddressMapper.INSTANCE.addressToUpdateResponse(addressRepository.save(address));}).orElseThrow(() -> new BusinessException("No address was found for this id. Transaction failed."));
    }

    @Override
    public String updateDefaultAddressById(int id) {
        Address address = addressRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No address was found for this id."));
        List<Address> addresses = addressRepository.findByCustomerIdAndDefaultAddressTrue(address.getCustomer().getId());
        if(addresses.size()==0){
            address.setDefaultAddress(true);
            addressRepository.save(address);
            return "Address set default";
        }
        else if (addresses.size()==1){
            if(addresses.get(0)==address){
                return "This address already deafult";
            }
            address.setDefaultAddress(true);
            addressRepository.save(address);
            address = addresses.get(0);
            address.setDefaultAddress(false);
            addressRepository.save(address);
            return "Address set default";
        }

        return "Address setting error";
    }

    @Override
    public String deleteByIdAddress(int id) {
        Address address= addressRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No address was found for this id. Transaction failed."));
        if(address.isDefaultAddress()){
            return "The address that you want to delete is a default address. Please, change default address then try again";
        }
        List<Address> addresses = addressRepository.findByCustomerIdAndStatusTrue(address.getCustomer().getId());
        if(addresses.size()==1){
            return "Customer should have at least one address";
        }
        address.setStatus(false);
        addressRepository.save(address);
        return "Address deleted.";
    }

    @Override
    public GetAddressByIdResponse getAddressById(int id) {
        return AddressMapper.INSTANCE.addressByIdToGetResponse(addressRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No address was found for this id.")));
    }

    @Override
    public List<GetAddressesByCustomerIdResponse> getAddressByCustomerId(int id) {
        return AddressMapper.INSTANCE.addressByCustomerIdToGetResponse(addressRepository.findByCustomerIdAndStatusTrue(id));
    }

    @Override
    public List<GetAllAddressesResponse> getAllAddresses() {
        return AddressMapper.INSTANCE.addressesToGetResponse(addressRepository.findByStatusTrue());
    }
}
