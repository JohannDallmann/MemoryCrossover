package de.neuefische.backend.service;

import de.neuefische.backend.model.MongoUser;
import de.neuefische.backend.repository.MongoUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {

    private final MongoUserRepo repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser optionalMongoUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(optionalMongoUser.getUsername(), optionalMongoUser.getPassword(), List.of());
    }
}
