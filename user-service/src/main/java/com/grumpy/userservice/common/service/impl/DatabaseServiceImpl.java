package com.grumpy.userservice.common.service.impl;

import com.grumpy.userservice.common.entity.UserEntity;
import com.grumpy.userservice.common.exception.EntityNotFoundException;
import com.grumpy.userservice.common.repository.UserDetailsRepo;
import com.grumpy.userservice.common.service.DatabaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    private final UserDetailsRepo userDetailsRepo;

    @Override
    public UserEntity getUser(final String username) {
        return userDetailsRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    public UserEntity saveUserDetails(UserEntity userEntity) {
        log.debug("saving user in database");
        return userDetailsRepo.save(userEntity);
    }
}
