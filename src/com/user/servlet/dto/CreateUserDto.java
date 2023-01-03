package com.user.servlet.dto;

import lombok.Builder;
import lombok.Value;

import javax.servlet.http.Part;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    Part image;
    String email;
    String password;
    String role;
    String gender;

}
