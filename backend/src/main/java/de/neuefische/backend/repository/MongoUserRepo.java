package de.neuefische.backend.repository;

import de.neuefische.backend.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUserRepo extends MongoRepository<MongoUser, String> {

    public Optional<MongoUser> findByUsername(String username);
}
