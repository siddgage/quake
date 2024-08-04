package com.grumpy.userservice.service;

import com.grumpy.userservice.authentication.model.AuthenticationRequest;
import com.grumpy.userservice.authentication.model.AuthenticationResponse;
import com.grumpy.userservice.model.UserModel;

public interface AuthenticationService {
    AuthenticationResponse createUser(UserModel userModel);

    AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest);
}
