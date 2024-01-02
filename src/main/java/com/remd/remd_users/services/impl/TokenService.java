package com.remd.remd_users.services.impl;

import com.remd.remd_users.models.Token;
import com.remd.remd_users.models.Users;
import com.remd.remd_users.proxies.NotificationsProxy;
import com.remd.remd_users.repository.TokenRepository;
import com.remd.remd_users.repository.UserRepository;
import com.remd.remd_users.services.IServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService implements IServiceToken {
    @Autowired
    private TokenRepository resetPasswordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationsProxy notificationsProxy;
    @Override
    public boolean verifyToken(String token) {
        Token _token = resetPasswordRepository.findByToken(token);
        if (!_isTokenExpired(_token) && userRepository.existsByEmail(_token.getUser().getEmail())) return true;
        return false;
    }

    public boolean _isTokenExpired(Token token){
        Date date = new Date();
        Integer difference = date.getMinutes() - token.getCreatedDate().getMinutes();
        return !((difference < 2) && date.getDate()==token.getCreatedDate().getDate());
    }

    @Override
    public Token save(String email, Users users) {
        Token token = new Token();
//        token.setCreatedDate(new Localdatet);
        token.setToken(UUID.randomUUID().toString().substring(0, 6));
        token.setUser(users);
        notificationsProxy.sendToken(users.getUserName(), users.getEmail(), token.getToken());
        return resetPasswordRepository.save(token);

    }
}
