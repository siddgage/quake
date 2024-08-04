package com.grumpy.userservice.controller;

import com.grumpy.userservice.authentication.model.AuthenticationRequest;
import com.grumpy.userservice.authentication.model.AuthenticationResponse;
import com.grumpy.userservice.model.UserModel;
import com.grumpy.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.grumpy.quakelib.constant.URLConstant.V1;
import static com.grumpy.userservice.constant.UserUrlConstant.*;

@RestController
@RequestMapping(V1 + USER_BASE_URL + AUTHENTICATION)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = SIGN_UP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserModel userModel) {
        log.debug("sign up user: {}", userModel.toString());
        return new ResponseEntity<>(authenticationService.createUser(userModel), HttpStatus.CREATED);
    }

    @PostMapping(value = LOGIN_IN, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody AuthenticationRequest request) {
        log.debug("logging in user: {}", request.getUsername());
        return new ResponseEntity<>(authenticationService.loginUser(request), HttpStatus.OK);
    }
}
