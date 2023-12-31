package com.kftech.ecommercebackend.api.controller.auth;

import com.kftech.ecommercebackend.api.model.RegistrationBody;
import com.kftech.ecommercebackend.exception.UserAlreadyExistException;
import com.kftech.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {

        try {
            userService.registerUser(registrationBody);
            return new ResponseEntity<>("Registration done.", HttpStatus.OK);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>("Something went wrong. Try again.", HttpStatus.BAD_REQUEST);
        }

    }

}
