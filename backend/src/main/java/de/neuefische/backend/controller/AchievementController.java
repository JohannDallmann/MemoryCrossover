package de.neuefische.backend.controller;

import de.neuefische.backend.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import de.neuefische.backend.model.Achievement;

@RestController
@RequestMapping("/api/randm/achievement")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;


    @PostMapping("/earned")
    public void saveAchievement(@RequestBody Achievement achievement) {
        achievementService.saveAchievement(achievement);
    }
}
