package com.turkcell.authserver.business.abstracts;

import com.turkcell.authserver.business.Dto.requests.user.AddRequestUser;
import com.turkcell.authserver.business.Dto.requests.user.GetRequestUser;
import com.turkcell.authserver.business.Dto.requests.user.GetRequestUserFromToken;
import com.turkcell.authserver.business.Dto.responses.user.AddResponseUser;
import com.turkcell.authserver.business.Dto.responses.user.GetResponseUser;
import com.turkcell.authserver.business.Dto.responses.user.GetResponseUserFromToken;

public interface AuthService {
    AddResponseUser register(AddRequestUser addRequestUser);
    GetResponseUser login(GetRequestUser getRequestUser);
    GetResponseUserFromToken getUserFromToken(GetRequestUserFromToken getRequestUserFromToken);
}
