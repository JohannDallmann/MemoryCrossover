package de.neuefische.backend.repository;

import de.neuefische.backend.model.Achievement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AchievementRepository extends MongoRepository<Achievement, String> {

}