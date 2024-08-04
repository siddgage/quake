package com.grumpy.userservice.authentication.service.impl;

import com.grumpy.userservice.authentication.model.AuthenticationRequest;
import com.grumpy.userservice.authentication.model.AuthenticationResponse;
import com.grumpy.userservice.common.entity.UserEntity;
import com.grumpy.userservice.common.enums.Role;
import com.grumpy.userservice.common.model.UserModel;
import com.grumpy.userservice.authentication.service.AuthenticationService;
import com.grumpy.userservice.common.service.DatabaseService;
import com.grumpy.userservice.authentication.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final DatabaseService databaseService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse createUser(UserModel userModel) {
        UserEntity userEntity = UserEntity.builder()
                .username(userModel.getUsername())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .role(Role.USER)
                .build();

        databaseService.saveUserDetails(userEntity);

        return new AuthenticationResponse(jwtService.generateToken(userEntity));
    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        log.debug("authenticated successfully");
        var userEntity = databaseService.getUser(request.getUsername());

        return new AuthenticationResponse(jwtService.generateToken(userEntity));
    }
}
