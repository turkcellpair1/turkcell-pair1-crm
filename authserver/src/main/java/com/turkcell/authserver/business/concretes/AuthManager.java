package com.turkcell.authserver.business.concretes;

import com.turkcell.authserver.business.Dto.requests.user.RequestRegisterUser;
import com.turkcell.authserver.business.Dto.requests.user.RequestLoginUser;
import com.turkcell.authserver.business.Dto.requests.user.RequestUserFromToken;
import com.turkcell.authserver.business.Dto.responses.user.ResponseRegisterUser;
import com.turkcell.authserver.business.Dto.responses.user.ResponseLoginUser;
import com.turkcell.authserver.business.Dto.responses.user.ResponseUserFromToken;
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
    public ResponseRegisterUser registerUser(RequestRegisterUser requestRegisterUser) {
        User user = new User();
        user.setEmail(requestRegisterUser.getEmail());
        user.setPassword(passwordEncoder.encode(requestRegisterUser.getPassword()));
        user.setRole(this.roleService.getRole(requestRegisterUser.getRole()));
        User savedUser = userService.addUser(user);
        ResponseRegisterUser responseRegisterUser = new ResponseRegisterUser();
        responseRegisterUser.setId(savedUser.getId());
        responseRegisterUser.setEmail(savedUser.getEmail());
        responseRegisterUser.setRole(savedUser.getRole().getRole());
        return responseRegisterUser;
    }

    @Override
    public ResponseLoginUser loginUser(RequestLoginUser requestLoginUser) {
        // TODO: Handle Exception.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestLoginUser.getEmail(), requestLoginUser.getPassword()));

        if (!authentication.isAuthenticated())
            throw new RuntimeException("E-posta ya da şifre yanlış");
        UserDetails user = userService.loadUserByUsername(requestLoginUser.getEmail());
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user
                .getAuthorities()
                .stream()
                .map((role) -> role.getAuthority())
                .toList();
        claims.put("roles", roles);
        ResponseLoginUser responseLoginUser = new ResponseLoginUser();
        responseLoginUser.setToken(jwtService.generateToken(requestLoginUser.getEmail(), claims));
        responseLoginUser.setRole(roles.get(0));
        responseLoginUser.setId(this.userService.getUserIdByEmail(requestLoginUser.getEmail()));
        responseLoginUser.setEmail(requestLoginUser.getEmail());
        return responseLoginUser;
    }

    @Override
    public ResponseUserFromToken userFromToken(RequestUserFromToken requestUserFromToken) {

        if (!jwtService.validateToken(requestUserFromToken.getToken()))
            throw new RuntimeException("E-posta ya da şifre yanlış");

        String token = requestUserFromToken.getToken();

        ResponseUserFromToken responseUserFromToken = new ResponseUserFromToken();
        String email = jwtService.extractUsername(token);
        responseUserFromToken.setEmail(email);
        responseUserFromToken.setId(this.userService.getUserIdByEmail(email));
        responseUserFromToken.setRole(jwtService.extractRoles(token).get(0));

        return responseUserFromToken;

    }


}
