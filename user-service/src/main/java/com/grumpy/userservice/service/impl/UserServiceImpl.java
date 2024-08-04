package com.grumpy.userservice.service.impl;

import com.grumpy.userservice.entity.UserEntity;
import com.grumpy.userservice.service.DatabaseService;
import com.grumpy.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final DatabaseService databaseService;

    @Override
    public UserEntity getUser(String username) {
        return databaseService.getUser(username);
    }


}
