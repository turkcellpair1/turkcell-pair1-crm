package com.turkcell.authserver.business.Dto.requests.user;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRequestUser {
    private String email;
    private String password;
    private String role;
}
