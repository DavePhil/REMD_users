package com.remd.remd_users.services;

import com.remd.remd_users.models.Token;
import com.remd.remd_users.models.Users;

public interface IServiceToken {
    boolean verifyToken(String token);

    Token save(String email, Users users);
}
