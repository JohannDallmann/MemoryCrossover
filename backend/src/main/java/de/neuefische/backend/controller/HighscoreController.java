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
        return highscoreService.findAll();
    }

    @GetMapping("/sorted/score/asc")
    public List<Score> getSortedHighscoresAscending() {
        return highscoreService.findAllByOrderByScoreAsc();
    }

    @GetMapping("/sorted/score/desc")
    public List<Score> getSortedHighscoresDescending() {
        return highscoreService.findAllByOrderByScoreDesc();
    }

    @GetMapping("/sorted/timestamp/asc")
    public List<Score> getHighscoresSortedByTimestampAscending() {
        return highscoreService.findAllByOrderByTimestampAsc();
    }

    @GetMapping("/sorted/timestamp/desc")
    public List<Score> getHighscoresSortedByTimestampDescending() {
        return highscoreService.findAllByOrderByTimestampDesc();
    }


}
