package com.turkcell.authserver.business.rules;

import com.turkcell.authserver.core.exceptions.BusinessException;
import com.turkcell.authserver.dataAccess.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleBusinessRules {
    private final RoleRepository roleRepository;
    public void checkIfRoleExist(String role) {
        if(!this.roleRepository.existsByRole(role)) {
            throw new BusinessException("Role does not exist!");
        }
    }
}
