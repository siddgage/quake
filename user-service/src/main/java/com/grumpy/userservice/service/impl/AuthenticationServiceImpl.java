package com.grumpy.userservice.service.impl;

import com.grumpy.userservice.authentication.model.AuthenticationRequest;
import com.grumpy.userservice.authentication.model.AuthenticationResponse;
import com.grumpy.userservice.entity.UserEntity;
import com.grumpy.userservice.enums.Role;
import com.grumpy.userservice.model.UserModel;
import com.grumpy.userservice.service.AuthenticationService;
import com.grumpy.userservice.service.DatabaseService;
import com.grumpy.userservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {

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
                .age(userModel.getAge())
                .email(userModel.getEmail())
                .address(userModel.getAddress())
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

        var userEntity = databaseService.getUser(request.getUsername());

        return new AuthenticationResponse(jwtService.generateToken(userEntity));
    }
}
