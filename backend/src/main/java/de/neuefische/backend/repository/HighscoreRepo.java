package de.neuefische.backend.repository;

import de.neuefische.backend.model.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
@AllArgsConstructor
@Data
public class HighscoreRepo {
    private List<Score> highScoreList = new ArrayList<>();

    public List<Score> addScore(Score score) {
        highScoreList.add(score);
        return highScoreList;
    }

    public List<Score> getScoresSortedAscendent() {
        List<Score> sortedHighscores = new ArrayList<>(highScoreList);
        Collections.sort(sortedHighscores, Comparator.comparingInt(Score::getScore));
        return sortedHighscores;
    }

    public List<Score> getScoresSortedDescendent() {
        List<Score> sortedHighscores = new ArrayList<>(highScoreList);
        Collections.sort(sortedHighscores, Comparator.comparingInt(Score::getScore).reversed());
        return sortedHighscores;
    }

    public List<Score> getScoresSortedByTimestamp() {
        List<Score> sortedHighscores = new ArrayList<>(highScoreList);
        Collections.sort(sortedHighscores, Comparator.comparing(Score::getTimestamp));
        return sortedHighscores;
    }
}
