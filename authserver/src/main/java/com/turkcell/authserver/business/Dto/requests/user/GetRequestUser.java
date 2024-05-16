package com.turkcell.authserver.business.Dto.requests.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestUser {
    private String email;
    private String password;
}
