package com.example.jwtdemo.service;

import com.example.jwtdemo.domain.AppUser;
import com.example.jwtdemo.domain.Role;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    AppUser saveUser(AppUser user);

    AppUser getUser(String username);

    List<AppUser> getAllUsers();

    Role saveRole(Role role);

    Role getRole(String roleName);

    void addUserToRole(String username, String rolename);
}
