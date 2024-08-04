package com.grumpy.userservice.service;

import com.grumpy.userservice.entity.UserEntity;

public interface DatabaseService {

    UserEntity getUser(String username);

    UserEntity saveUserDetails(UserEntity userEntity);
}
