package com.jromero.redsocial.controllers;

import java.util.List;

import com.jromero.redsocial.dto.LoginDTO;
import com.jromero.redsocial.dto.TokenResponseDTO;
import com.jromero.redsocial.iservice.IUserService;
import com.jromero.redsocial.models.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private IUserService service;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        try {
            List<UserModel> users = service.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserModel user) {
        try {
            service.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Registrado satisfactoriamente");
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email y/o nick ya existen en base de datos");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor. Intente de nuevo");
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginData) {
        try {
            TokenResponseDTO response = service.login(loginData);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (BadCredentialsException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales invalidas");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor. Intente de nuevo");
        }
    }

}
