package com.example.springbootsecurity.controller;

import com.example.springbootsecurity.entity.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Linus", "Torvalds"),
            new Developer(2L, "Pavel", "Durov"),
            new Developer(3L, "Mark", "Cukerbergs"),
            new Developer(4L, "Artemii", "Aleksenko")
    ).collect(Collectors.toList());

    @GetMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public List<Developer> getAll() {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream()
                .filter(dev -> dev.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id) {
        DEVELOPERS.removeIf(dev -> dev.getId().equals(id));
    }
}
