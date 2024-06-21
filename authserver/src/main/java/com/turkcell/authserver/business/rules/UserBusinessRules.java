package com.turkcell.authserver.business.rules;

import com.turkcell.authserver.core.exceptions.BusinessException;
import com.turkcell.authserver.dataAccess.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;
    public void checkIfEmailExists(String email) {
        if(this.userRepository.existsByEmail(email)) {
            throw new BusinessException("Email is already taken!");
        }
    }
}
