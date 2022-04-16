package com.example.jwtdemo;

import com.example.jwtdemo.domain.AppUser;
import com.example.jwtdemo.domain.Role;
import com.example.jwtdemo.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AppUserService userService) {
        return args -> {
          userService.saveRole(new Role(null, "ROLE_USER"));
          userService.saveRole(new Role(null, "ROLE_MANAGER"));
          userService.saveRole(new Role(null, "ROLE_ADMIN"));
          userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

          userService.saveUser(new AppUser(null, "Ivan", "iva777", "passIvan", new ArrayList<>()));
          userService.saveUser(new AppUser(null, "Boris", "bor777", "passBor", new ArrayList<>()));
          userService.saveUser(new AppUser(null, "Kate", "kate777", "passKate", new ArrayList<>()));
          userService.saveUser(new AppUser(null, "Sam", "sam777", "passSam", new ArrayList<>()));

          userService.addUserToRole("iva777", "ROLE_USER");
          userService.addUserToRole("iva777", "ROLE_MANAGER");
          userService.addUserToRole("bor777", "ROLE_MANAGER");
          userService.addUserToRole("kate777", "ROLE_ADMIN");
          userService.addUserToRole("sam777", "ROLE_USER");
          userService.addUserToRole("sam777", "ROLE_ADMIN");
          userService.addUserToRole("sam777", "ROLE_SUPER_ADMIN");
        };
    }

    @Bean
    protected PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
