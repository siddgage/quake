package com.grumpy.userservice.usertask.service;

import com.grumpy.userservice.common.entity.UserEntity;

public interface UserService {

    UserEntity getUser(String username);
}
