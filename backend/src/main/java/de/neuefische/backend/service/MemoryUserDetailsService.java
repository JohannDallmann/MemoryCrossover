package de.neuefische.backend.service;

import de.neuefische.backend.model.MemoryUser;
import de.neuefische.backend.repository.MemoryUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoryUserDetailsService implements UserDetailsService {

    private final MemoryUserRepo repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemoryUser optionalMemoryUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(optionalMemoryUser.getUsername(), optionalMemoryUser.getPassword(), List.of());
    }
}
