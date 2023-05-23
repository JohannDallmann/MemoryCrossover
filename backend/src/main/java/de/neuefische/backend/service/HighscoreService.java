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

    public List<Score> addScore(Score score) {
        score.setTimestamp(LocalDateTime.now());
        return highscoreRepo.addScore(score);
    }

    public List<Score> getScoresSortedAscendent() {
        return highscoreRepo.getScoresSortedAscendent();
    }

    public List<Score> getScoresSortedDescendent() {
        return highscoreRepo.getScoresSortedDescendent();
    }

    public List<Score> getScoresSortedByTimestamp() {
        return highscoreRepo.getScoresSortedByTimestamp();
    }
}
