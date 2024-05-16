package com.turkcell.authserver.business.concretes;

import com.turkcell.authserver.business.abstracts.RoleService;
import com.turkcell.authserver.dataAccess.RoleRepository;
import com.turkcell.authserver.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getRole(String role) {
        return this.roleRepository.findByRole(role).orElseThrow();
    }
}
