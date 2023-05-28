package de.neuefische.backend.repository;

import de.neuefische.backend.model.GameResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HighscoreRepo extends MongoRepository<GameResult, String> {
    List<GameResult> findAllByOrderByScoreAsc();

    List<GameResult> findAllByOrderByScoreDesc();

    List<GameResult> findAllByOrderByTimestampAsc();

    List<GameResult> findAllByOrderByTimestampDesc();
}
