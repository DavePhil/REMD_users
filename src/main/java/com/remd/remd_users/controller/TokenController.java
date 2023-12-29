package com.remd.remd_users.controller;

import com.remd.remd_users.models.Token;
import com.remd.remd_users.models.Users;
import com.remd.remd_users.services.impl.TokenService;
import com.remd.remd_users.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("generateToken/{email}")
    public ResponseEntity generate(@PathVariable("email") String email){
        Users users = userService.getUser(email).get();
        if (users == null) return new ResponseEntity<>("l'utilisateur n'existe pas", HttpStatus.BAD_REQUEST);
        Token token = tokenService.save(email, users);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("verifyToken/{token}")
    public ResponseEntity<?> verifyToken(@PathVariable("token") String token){
        Boolean isVerify = tokenService.verifyToken(token);
        if (isVerify) return  new ResponseEntity<>("Token verifié", HttpStatus.OK);
        else return new ResponseEntity<>("Token incorrect ou expiré", HttpStatus.OK);
    }
}
