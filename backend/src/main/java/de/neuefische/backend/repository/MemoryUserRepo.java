package de.neuefische.backend.repository;

import de.neuefische.backend.model.MemoryUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoryUserRepo extends MongoRepository<MemoryUser, String> {

    public Optional<MemoryUser> findByUsername(String username);
}
