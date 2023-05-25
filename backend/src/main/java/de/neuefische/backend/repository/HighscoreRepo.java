package de.neuefische.backend.repository;

import de.neuefische.backend.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HighscoreRepo extends MongoRepository<Score, String> {

}
