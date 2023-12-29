package com.remd.remd_users.services.impl;

import com.remd.remd_users.models.Users;
import com.remd.remd_users.proxies.NotificationsProxy;
import com.remd.remd_users.repository.UserRepository;
import com.remd.remd_users.services.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IServiceUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationsProxy notificationsProxy;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public Users CreateUser(Users user){
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        notificationsProxy.newAccount(user.getUserName(), user.getEmail());
        userRepository.save(user);
        return user;
    }

    public Boolean verifyPassword(String password, String passwordHache){
        return bCryptPasswordEncoder.matches(password,passwordHache);
    }

    public Optional<Users> getUser(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<Users> getById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<Users> findAll(){return userRepository.findAll();}
}
