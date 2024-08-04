package com.grumpy.userservice.service;

import com.grumpy.userservice.entity.UserEntity;

public interface UserService {

    UserEntity getUser(String username);
}
