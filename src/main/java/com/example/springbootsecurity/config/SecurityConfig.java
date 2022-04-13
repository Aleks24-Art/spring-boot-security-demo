package com.example.springbootsecurity.config;

import com.example.springbootsecurity.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/").permitAll()
                /*
                 Вместо Matchers используем аннотацию @PreAuthorise
                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permissions.DEVELOPERS_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(Permissions.DEVELOPERS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(Permissions.DEVELOPERS_WRITE.getPermission())*/
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("guest")
                        .password(passwordEncoder().encode("guest"))
                        .authorities(Role.GUEST.getAuthorities())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
