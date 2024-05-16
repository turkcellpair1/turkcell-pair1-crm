package com.turkcell.authserver.business.abstracts;

import com.turkcell.authserver.entities.Role;

public interface RoleService {
    Role getRole(String role);
}
