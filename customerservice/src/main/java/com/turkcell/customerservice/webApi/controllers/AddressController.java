package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.AddressService;
import com.turkcell.customerservice.business.dto.requests.address.AddAddressRequest;
import com.turkcell.customerservice.business.dto.requests.address.UpdateAddressRequest;
import com.turkcell.customerservice.business.dto.responses.address.*;
import com.turkcell.customerservice.business.dto.responses.customer.AddCustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping()
    public ResponseEntity<AddAddressResponse> addAddress(@RequestBody @Valid AddAddressRequest request){
        AddAddressResponse response = addressService.addAddress(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping()
    public UpdateAddressResponse updateAddress(@RequestBody @Valid UpdateAddressRequest request){
        return addressService.updateAddress(request);
    }
    @PutMapping("updateDefaultAddress/{id}")
    public String updateDefaultAddress(@PathVariable int id){
        return addressService.updateDefaultAddressById(id);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteByIdAddress(@PathVariable int id){
        return addressService.deleteByIdAddress(id);
    }

    @GetMapping("{id}")
    public GetAddressByIdResponse getAddressById(@PathVariable int id){
        return addressService.getAddressById(id);
    }

    @GetMapping("getAddressByCustomerId/{id}")
    public List<GetAddressesByCustomerIdResponse> getAddressesByCustomerId(@PathVariable int id){
        return addressService.getAddressByCustomerId(id);
    }

    @GetMapping("getAllAddresses")
    public List<GetAllAddressesResponse> getAllAddresses(){
        return addressService.getAllAddresses();
    }
}
