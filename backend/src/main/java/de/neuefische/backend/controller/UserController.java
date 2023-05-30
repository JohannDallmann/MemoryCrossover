package de.neuefische.backend.controller;

import de.neuefische.backend.service.MemoryUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MemoryUserDetailsService service;

    @GetMapping("/me")
    public String getMe1(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return "AnonymousUser";
    }

    @GetMapping("/me2")
    public String getMe2() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
