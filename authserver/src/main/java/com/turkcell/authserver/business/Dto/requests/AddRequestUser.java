package com.turkcell.authserver.business.Dto.requests;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRequestUser {
    private String email;
    private String password;
}
