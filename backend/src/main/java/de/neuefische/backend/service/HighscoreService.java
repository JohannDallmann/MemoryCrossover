package de.neuefische.backend.service;

import de.neuefische.backend.model.Score;
import de.neuefische.backend.repository.HighscoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HighscoreService {
    private final HighscoreRepo highscoreRepo;
    private final GenerateUUIDService generateUUIDService;

    public List<Score> addScore(Score score) {
        score.setId(generateUUIDService.generateUUID());
        score.setTimestamp(LocalDateTime.now());
        highscoreRepo.save(score);
        return highscoreRepo.findAll();
    }

    public List<Score> findAll() {
        return highscoreRepo.findAll();
    }

    public List<Score> findAllByOrderByScoreAsc() {
        return highscoreRepo.findAllByOrderByScoreAsc();
    }

    public List<Score> findAllByOrderByScoreDesc() {
        return highscoreRepo.findAllByOrderByScoreDesc();
    }

    public List<Score> findAllByOrderByTimestampAsc() {
        return highscoreRepo.findAllByOrderByTimestampAsc();
    }

    public List<Score> findAllByOrderByTimestampDesc() {
        return highscoreRepo.findAllByOrderByTimestampDesc();
    }
}
