package de.neuefische.backend.repository;

import de.neuefische.backend.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HighscoreRepo extends MongoRepository<Score, String> {
    List<Score> findAllByOrderByScoreAsc();

    List<Score> findAllByOrderByScoreDesc();

    List<Score> findAllByOrderByTimestampAsc();

    List<Score> findAllByOrderByTimestampDesc();
}
