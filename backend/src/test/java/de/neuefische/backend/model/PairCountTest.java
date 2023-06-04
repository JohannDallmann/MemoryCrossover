package de.neuefische.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairCountTest {

    @Test
    void testCalculatePairCount_case_Even() {
        // given
        int numberOfPairs = 2;
        int expectedNumberOfCardsRickAndMorty = 2;
        int expectedNumberOfCardsGameOfThrones = 1;

        // when
        PairCount pairCount = new PairCount();
        pairCount.calculatePairCount(numberOfPairs);

        // then
        assertEquals(expectedNumberOfCardsRickAndMorty, pairCount.getNumberOfCardsRickAndMorty());
        assertEquals(expectedNumberOfCardsGameOfThrones, pairCount.getNumberOfPairsGameOfThrones());
    }

    @Test
    void testCalculatePairCount_case_Odd() {
        // given
        int numberOfPairs = 5;

        // when
        PairCount pairCount = new PairCount();
        pairCount.calculatePairCount(numberOfPairs);

        // then
        assertTrue(pairCount.getNumberOfCardsRickAndMorty() >= 4 && pairCount.getNumberOfCardsRickAndMorty() <= 6);
        assertTrue(pairCount.getNumberOfPairsGameOfThrones()== 2 || pairCount.getNumberOfPairsGameOfThrones() == 3);
        assertEquals(numberOfPairs, pairCount.getNumberOfCardsRickAndMorty() / 2 + pairCount.getNumberOfPairsGameOfThrones());
    }

}