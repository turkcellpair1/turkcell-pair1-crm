package com.turkcell.authserver.business.Dto.responses.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddResponseUser {
    private int id;
    private String email;
    private String role;
}


