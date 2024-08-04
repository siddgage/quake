package com.grumpy.userservice.model;

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
    private int age;
    private String email;
    private String address;
    private String password;
}
