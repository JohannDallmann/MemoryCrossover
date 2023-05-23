package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private String playerName;
    private int score;
    private LocalDateTime timestamp;
}
