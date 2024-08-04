package com.grumpy.userservice.common.service;

import com.grumpy.userservice.common.entity.UserEntity;

public interface DatabaseService {

    UserEntity getUser(String username);

    UserEntity saveUserDetails(UserEntity userEntity);
}
