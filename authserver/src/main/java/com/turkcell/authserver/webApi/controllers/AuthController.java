package com.turkcell.authserver.webApi.controllers;

import com.turkcell.authserver.business.Dto.requests.user.RequestRegisterUser;
import com.turkcell.authserver.business.Dto.requests.user.RequestLoginUser;
import com.turkcell.authserver.business.Dto.requests.user.RequestUserFromToken;
import com.turkcell.authserver.business.Dto.responses.user.ResponseRegisterUser;
import com.turkcell.authserver.business.Dto.responses.user.ResponseLoginUser;
import com.turkcell.authserver.business.Dto.responses.user.ResponseUserFromToken;
import com.turkcell.authserver.business.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/user/customer/register")
    public ResponseEntity<ResponseRegisterUser> registerCustomer(@RequestBody RequestRegisterUser requestRegisterUser) {
        requestRegisterUser.setRole("customer");
        ResponseRegisterUser response = this.authService.registerUser(requestRegisterUser);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/user/admin/register")
    public ResponseEntity<ResponseRegisterUser> registerAdmin(@RequestBody RequestRegisterUser requestRegisterUser) {
        requestRegisterUser.setRole("admin");
        ResponseRegisterUser response = this.authService.registerUser(requestRegisterUser);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/customer/login")
    public ResponseEntity<ResponseLoginUser> loginCustomer(@RequestBody RequestLoginUser requestLoginUser) {
        ResponseLoginUser response = this.authService.loginUser(requestLoginUser);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/admin/login")
    public ResponseEntity<ResponseLoginUser> loginAdmin(@RequestBody RequestLoginUser requestLoginUser) {
        ResponseLoginUser response = this.authService.loginUser(requestLoginUser);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/token")
    public ResponseEntity<ResponseUserFromToken> getUserFromToken(@RequestBody RequestUserFromToken requestUserFromToken) {
        ResponseUserFromToken response = this.authService.userFromToken(requestUserFromToken);
        return ResponseEntity.ok().body(response);
    }


}
