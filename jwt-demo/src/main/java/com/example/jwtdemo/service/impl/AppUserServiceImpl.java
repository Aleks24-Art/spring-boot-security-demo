package com.example.jwtdemo.service.impl;

import com.example.jwtdemo.domain.AppUser;
import com.example.jwtdemo.domain.Role;
import com.example.jwtdemo.repo.AppUserRepo;
import com.example.jwtdemo.repo.RoleRepo;
import com.example.jwtdemo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = getUser(username);
        List<SimpleGrantedAuthority> authority = new ArrayList<>();
        appUser.getRoles().forEach(
                role -> authority.add(new SimpleGrantedAuthority(role.getName()))
        );
        return new User(appUser.getUsername(), appUser.getPassword(), authority);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public AppUser getUser(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepo.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found!"));
    }

    @Override
    public void addUserToRole(String username, String roleName) {
        AppUser appUser = getUser(username);
        Role role = getRole(roleName);
        appUser.getRoles().add(role);
    }
}
