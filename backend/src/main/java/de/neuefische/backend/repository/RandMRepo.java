package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandMRepo extends MongoRepository<RandMCharacter,String> {

}
