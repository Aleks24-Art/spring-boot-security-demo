package com.example.springbootsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
}
