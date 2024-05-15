package com.turkcell.authserver.business.concretes;

import com.turkcell.authserver.business.Dto.requests.AddRequestUser;
import com.turkcell.authserver.business.Dto.requests.GetRequestUser;
import com.turkcell.authserver.business.abstracts.AuthService;
import com.turkcell.authserver.business.abstracts.UserService;
import com.turkcell.authserver.core.services.JwtService;
import com.turkcell.authserver.entities.User;
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
    private final JwtService jwtService;

    @Override
    public void register(AddRequestUser addRequestUser) {
        User user = new User();
        user.setEmail(addRequestUser.getEmail());
        user.setPassword(passwordEncoder.encode(addRequestUser.getPassword()));
        userService.addUser(user);
    }

    @Override
    public String login(GetRequestUser getRequestUser) {
        // TODO: Handle Exception.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(getRequestUser.getEmail(), getRequestUser.getPassword()));

        if(!authentication.isAuthenticated())
            throw new RuntimeException("E-posta ya da şifre yanlış");


        UserDetails user = userService.loadUserByUsername(getRequestUser.getEmail());
        Map<String,Object> claims = new HashMap<>();

        List<String> roles = user
                .getAuthorities()
                .stream()
                .map((role) -> role.getAuthority())
                .toList();
        claims.put("roles", roles);



        return jwtService.generateToken(getRequestUser.getEmail(), claims);
    }



}
