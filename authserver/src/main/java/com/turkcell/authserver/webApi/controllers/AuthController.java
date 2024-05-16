package com.turkcell.authserver.webApi.controllers;

import com.turkcell.authserver.business.Dto.requests.user.AddRequestUser;
import com.turkcell.authserver.business.Dto.requests.user.GetRequestUser;
import com.turkcell.authserver.business.Dto.requests.user.GetRequestUserFromToken;
import com.turkcell.authserver.business.Dto.responses.user.AddResponseUser;
import com.turkcell.authserver.business.Dto.responses.user.GetResponseUser;
import com.turkcell.authserver.business.Dto.responses.user.GetResponseUserFromToken;
import com.turkcell.authserver.business.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/user/customer/register")
    public ResponseEntity<AddResponseUser> registerCustomer(@RequestBody AddRequestUser addRequestUser) {
        addRequestUser.setRole("customer");
        AddResponseUser response = this.authService.register(addRequestUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }


    @PostMapping("/user/admin/register")
    public ResponseEntity<AddResponseUser> registerAdmin(@RequestBody AddRequestUser addRequestUser) {
        addRequestUser.setRole("admin");
        AddResponseUser response = this.authService.register(addRequestUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/user/customer/login")
    public ResponseEntity<GetResponseUser> loginCustomer(@RequestBody GetRequestUser getRequestUser) {
        GetResponseUser response = this.authService.login(getRequestUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/user/admin/login")
    public ResponseEntity<GetResponseUser> loginAdmin(@RequestBody GetRequestUser getRequestUser) {
        GetResponseUser response = this.authService.login(getRequestUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/user")
    public ResponseEntity<GetResponseUserFromToken> getUserFromToken(@RequestBody GetRequestUserFromToken getRequestUserFromToken) {
        GetResponseUserFromToken response = this.authService.getUserFromToken(getRequestUserFromToken);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }



}
