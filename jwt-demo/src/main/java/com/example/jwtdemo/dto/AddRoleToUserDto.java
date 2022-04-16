package com.example.jwtdemo.dto;

import lombok.Data;

@Data
public class AddRoleToUserDto {
    private String username;
    private String roleName;
}
