package com.remd.remd_users.services;

import com.remd.remd_users.models.Users;

import java.util.List;
import java.util.Optional;

public interface IServiceUser {
     Users CreateUser(Users user);
     Boolean verifyPassword(String password, String passwordHache);
     Optional<Users> getUser(String email);
      Optional<Users> getById(Long id);
     void deleteUser(Long id);
     List<Users> findAll();
}
