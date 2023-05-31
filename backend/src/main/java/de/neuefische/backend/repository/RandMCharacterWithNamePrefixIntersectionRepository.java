package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacterWithNamePrefixIntersection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RandMCharacterWithNamePrefixIntersectionRepository extends MongoRepository<RandMCharacterWithNamePrefixIntersection, String> {
}
