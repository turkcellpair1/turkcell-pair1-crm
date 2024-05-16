package com.turkcell.authserver.business.abstracts;

import com.turkcell.authserver.business.Dto.requests.user.RequestRegisterUser;
import com.turkcell.authserver.business.Dto.requests.user.RequestLoginUser;
import com.turkcell.authserver.business.Dto.requests.user.RequestUserFromToken;
import com.turkcell.authserver.business.Dto.responses.user.ResponseRegisterUser;
import com.turkcell.authserver.business.Dto.responses.user.ResponseLoginUser;
import com.turkcell.authserver.business.Dto.responses.user.ResponseUserFromToken;

public interface AuthService {
    ResponseRegisterUser registerUser(RequestRegisterUser requestRegisterUser);
    ResponseLoginUser loginUser(RequestLoginUser requestLoginUser);
    ResponseUserFromToken userFromToken(RequestUserFromToken requestUserFromToken);
}
