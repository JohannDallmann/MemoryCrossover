package de.neuefische.backend.service;

import de.neuefische.backend.model.Achievement;
import de.neuefische.backend.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public void saveAchievement(Achievement achievement) {
        achievementRepository.save(achievement);
    }
}