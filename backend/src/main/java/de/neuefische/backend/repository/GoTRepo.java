package de.neuefische.backend.repository;

import de.neuefische.backend.model.GoTCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoTRepo extends MongoRepository<GoTCharacter,String> {

}
