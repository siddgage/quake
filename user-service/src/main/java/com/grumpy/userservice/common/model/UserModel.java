package com.grumpy.userservice.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
