package de.neuefische.backend.controller;

import de.neuefische.backend.model.GameResult;
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
    public List<GameResult> addScore(@RequestBody GameResult gameResult) {
        return highscoreService.save(gameResult);
    }

    @GetMapping
    public List<GameResult> getAllHighscores() {
        return highscoreService.findAll();
    }

    @GetMapping("/sorted/score/asc")
    public List<GameResult> getSortedHighscoresAscending() {
        return highscoreService.findAllByOrderByScoreAsc();
    }

    @GetMapping("/sorted/score/desc")
    public List<GameResult> getSortedHighscoresDescending() {
        return highscoreService.findAllByOrderByScoreDesc();
    }

    @GetMapping("/sorted/timestamp/asc")
    public List<GameResult> getHighscoresSortedByTimestampAscending() {
        return highscoreService.findAllByOrderByTimestampAsc();
    }

    @GetMapping("/sorted/timestamp/desc")
    public List<GameResult> getHighscoresSortedByTimestampDescending() {
        return highscoreService.findAllByOrderByTimestampDesc();
    }


}
