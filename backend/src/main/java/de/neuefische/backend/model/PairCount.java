package de.neuefische.backend.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class PairCount {
    private int numberOfCardsRickAndMorty;
    private int numberOfPairsGameOfThrones;

    public void calculatePairCount(int numberOfPairs) {
        int numberOfCardsTotal = numberOfPairs * 2;

        if (numberOfPairs % 2 == 0) {
            numberOfCardsRickAndMorty = numberOfCardsTotal / 2;
            numberOfPairsGameOfThrones = numberOfPairs / 2;
        } else {
            int remainder = 1;
            if (Math.random() < 0.5) {
                numberOfCardsRickAndMorty = 2 * (numberOfPairs / 2) + 2 * remainder;
                numberOfPairsGameOfThrones = numberOfPairs / 2;
            } else {
                numberOfCardsRickAndMorty = 2 * (numberOfPairs / 2);
                numberOfPairsGameOfThrones = numberOfPairs / 2 + remainder;
            }
        }
    }
}
