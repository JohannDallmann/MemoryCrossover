package de.neuefische.backend.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

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
            SecureRandom random = new SecureRandom();
            int remainder = 1;
            if (random.nextBoolean()) {
                numberOfCardsRickAndMorty = 2 * (numberOfPairs / 2) + 2 * remainder;
                numberOfPairsGameOfThrones = numberOfPairs / 2;
            } else {
                numberOfCardsRickAndMorty = 2 * (numberOfPairs / 2);
                numberOfPairsGameOfThrones = numberOfPairs / 2 + remainder;
            }
        }
    }
}
