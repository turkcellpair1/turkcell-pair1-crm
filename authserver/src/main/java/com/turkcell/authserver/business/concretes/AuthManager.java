package com.turkcell.authserver.business.concretes;

import com.turkcell.authserver.business.Dto.requests.user.AddRequestUser;
import com.turkcell.authserver.business.Dto.requests.user.GetRequestUser;
import com.turkcell.authserver.business.Dto.requests.user.GetRequestUserFromToken;
import com.turkcell.authserver.business.Dto.responses.user.AddResponseUser;
import com.turkcell.authserver.business.Dto.responses.user.GetResponseUser;
import com.turkcell.authserver.business.Dto.responses.user.GetResponseUserFromToken;
import com.turkcell.authserver.business.abstracts.AuthService;
import com.turkcell.authserver.business.abstracts.RoleService;
import com.turkcell.authserver.business.abstracts.UserService;
import com.turkcell.authserver.entities.User;
import com.turkcell.core.security.BaseJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BaseJwtService jwtService;
    private final RoleService roleService;

    @Override
    public AddResponseUser register(AddRequestUser addRequestUser) {
        User user = new User();
        user.setEmail(addRequestUser.getEmail());
        user.setPassword(passwordEncoder.encode(addRequestUser.getPassword()));
        user.setRole(this.roleService.getRole(addRequestUser.getRole()));
        User savedUser = userService.addUser(user);
        AddResponseUser addResponseUser = new AddResponseUser();
        addResponseUser.setId(savedUser.getId());
        addResponseUser.setEmail(savedUser.getEmail());
        addResponseUser.setRole(savedUser.getRole().getRole());
        return addResponseUser;
    }

    @Override
    public GetResponseUser login(GetRequestUser getRequestUser) {
        // TODO: Handle Exception.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(getRequestUser.getEmail(), getRequestUser.getPassword()));

        if (!authentication.isAuthenticated())
            throw new RuntimeException("E-posta ya da şifre yanlış");
        UserDetails user = userService.loadUserByUsername(getRequestUser.getEmail());
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user
                .getAuthorities()
                .stream()
                .map((role) -> role.getAuthority())
                .toList();
        claims.put("roles", roles);
        GetResponseUser getResponseUser = new GetResponseUser();
        getResponseUser.setToken(jwtService.generateToken(getRequestUser.getEmail(), claims));
        getResponseUser.setRole(roles.get(0));
        getResponseUser.setId(this.userService.getUserIdByEmail(getRequestUser.getEmail()));
        getResponseUser.setEmail(getRequestUser.getEmail());
        return getResponseUser;
    }

    @Override
    public GetResponseUserFromToken getUserFromToken(GetRequestUserFromToken getRequestUserFromToken) {

        if (!jwtService.validateToken(getRequestUserFromToken.getToken()))
            throw new RuntimeException("E-posta ya da şifre yanlış");

        String token = getRequestUserFromToken.getToken();

        GetResponseUserFromToken getResponseUserFromToken = new GetResponseUserFromToken();
        String email = jwtService.extractUsername(token);
        getResponseUserFromToken.setEmail(email);
        getResponseUserFromToken.setId(this.userService.getUserIdByEmail(email));
        getResponseUserFromToken.setRole(jwtService.extractRoles(token).get(0));

        return getResponseUserFromToken;

    }


}
