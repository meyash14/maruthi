package com.mav.authservice.model;

import lombok.Data;

@Data
public class UserRequest {

    private String email;
    private String password;
    private String role;
}
