package com.example.springbootsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permissions {

    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    private final String permission;
}
