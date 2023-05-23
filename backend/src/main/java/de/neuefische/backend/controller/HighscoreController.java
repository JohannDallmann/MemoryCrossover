package de.neuefische.backend.controller;

import de.neuefische.backend.model.Score;
import de.neuefische.backend.service.HighscoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/randm/highscore")
@RequiredArgsConstructor
public class HighscoreController {

    private final HighscoreService highscoreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Score> addScore(@RequestBody Score score) {
        return highscoreService.addScore(score);
    }

    @GetMapping
    public List<Score> getAllHighscores() {
        return highscoreService.getScoresSortedAscendent();
    }

    @GetMapping("/sorted/ascending")
    public List<Score> getSortedHighscoresAscending() {
        return highscoreService.getScoresSortedAscendent();
    }

    @GetMapping("/sorted/descending")
    public List<Score> getSortedHighscoresDescending() {
        return highscoreService.getScoresSortedDescendent();
    }

    @GetMapping(params = "sortBy=timestamp")
    public List<Score> getHighscoresSortedByTimestamp() {
        return highscoreService.getScoresSortedByTimestamp();
    }


}
