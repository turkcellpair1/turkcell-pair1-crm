package com.turkcell.authserver.business.abstracts;

import com.turkcell.authserver.business.Dto.requests.AddRequestUser;
import com.turkcell.authserver.business.Dto.requests.GetRequestUser;

public interface AuthService {
    void register(AddRequestUser addRequestUser);
    String login(GetRequestUser getRequestUser);
}
