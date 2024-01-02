package com.remd.remd_users.controller;

import com.remd.remd_users.models.Users;
import com.remd.remd_users.services.impl.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("create")
    public ResponseEntity<?> createAdmin(@RequestBody Users user){
        if (userService.getUser(user.getEmail()).isPresent()) return new ResponseEntity<>("Cette utilisateur existe déjà", HttpStatus.BAD_REQUEST);
        Users saveUser = userService.CreateUser(user); // Recuperation de l'instance à sauvegarder
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password ){
        Optional<Users> userSave = userService.getUser(email); // recuperation de l'utilisateur sauvegardé en base de données
        //Verification si le login est correct, si oui vérifiez si le mot de passe entré par l'utilisateur match avec celui hash en base de données
        if (!userSave.isPresent()) return new ResponseEntity<>("Identifiant ou mot de passe incorrect", HttpStatus.BAD_REQUEST);
        else if (!userService.verifyPassword(password, userSave.get().getPassword())) return new ResponseEntity<>("Identifiant ou mot de passe incorrect", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(userSave, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable("id") Long id){
        Users user = userService.getById(id).get();
        return user;
    }



}
