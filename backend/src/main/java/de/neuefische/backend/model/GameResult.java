package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("scores")
public class GameResult {
    @Id
    private String id;
    private String playerName;
    private int score;
    private int remainingTime;
    private int numberOfSteps;
    private LocalDateTime timestamp;
}
