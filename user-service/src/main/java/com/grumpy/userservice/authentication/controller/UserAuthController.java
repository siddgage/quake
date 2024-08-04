package com.grumpy.userservice.authentication.controller;

import com.grumpy.userservice.authentication.model.AuthenticationRequest;
import com.grumpy.userservice.authentication.model.AuthenticationResponse;
import com.grumpy.userservice.common.model.UserModel;
import com.grumpy.userservice.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.grumpy.quakelib.constant.URLConstant.V1;
import static com.grumpy.userservice.common.constant.UserUrlConstant.*;

@RestController
@RequestMapping(V1 + USER_BASE_URL + AUTHENTICATION)
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserAuthController {

    private final AuthenticationService authenticationService;

        @PostMapping(value = SIGNUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserModel userModel) {
        log.debug("sign up user: {}", userModel.toString());
        return new ResponseEntity<>(authenticationService.createUser(userModel), HttpStatus.CREATED);
    }

    @PostMapping(value = LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
        log.debug("logging in user: {}", request.getUsername());
        return new ResponseEntity<>(authenticationService.loginUser(request), HttpStatus.OK);
    }
}
