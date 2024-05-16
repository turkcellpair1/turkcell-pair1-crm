package com.turkcell.authserver.business.Dto.requests.user;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterUser {
    private String email;
    private String password;
    private String role;
}
