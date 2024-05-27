package com.turkcell.customerservice.business.rules;

import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;

    public void checkIfNationalityIdExist(String id){
        if (customerRepository.existsByNationalityId(id)){
            throw new BusinessException("Nationality ID already exists");
        }
    }

    public void checkIfUserIdExist(int id){
        if (customerRepository.existsByUserId(id)){
            throw new BusinessException("User ID already exists");
        }
    }
}
