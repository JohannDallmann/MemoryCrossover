package de.neuefische.backend.service;

import de.neuefische.backend.dto.CardDTO;
import de.neuefische.backend.model.BoardGenerationCondition;
import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.model.PairCount;
import de.neuefische.backend.model.RandMCharacter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class MixedCardSetServiceTest {

    RandMService randMService = mock(RandMService.class);
    GoTService goTService = mock(GoTService.class);

    PairCount pairCount = mock(PairCount.class);

    MixedCardSetService mixedCardSetService = new MixedCardSetService(randMService, goTService, pairCount);

    @Test
    void testGetRandomCharacters_at_least_two_pairs_are_generated() {
        // given
        List<RandMCharacter> randMCharacters = new ArrayList<>();
        RandMCharacter character1 = new RandMCharacter("uuid1", 1, "Character One", "Species 1", "image1");
        RandMCharacter character2 = new RandMCharacter("uuid2", 2, "Character Two", "Species 2", "image2");
        RandMCharacter character3 = new RandMCharacter("uuid3", 3, "Character Three", "Species 1", "image3");
        randMCharacters.add(character1);
        randMCharacters.add(character3);
        List<GoTCharacter> goTCharacters = new ArrayList<>();
        GoTCharacter character4 = new GoTCharacter("uuid4", 4, "Character Four", "Family 1", "imageUrl4");
        GoTCharacter character5 = new GoTCharacter("uuid5", 5, "Character Five", "Family 2", "imageUrl5");
        GoTCharacter character6 = new GoTCharacter("uuid6", 6, "Character Six", "Family 1", "imageUrl6");
        goTCharacters.add(character4);
        goTCharacters.add(character6);
        Mockito.<List<RandMCharacter>>when(randMService.generateBoardByCondition(2, 1, BoardGenerationCondition.SPECIES)).thenReturn(randMCharacters);
        when(goTService.getRandomCharacters(1)).thenReturn(goTCharacters);
        when(pairCount.getNumberOfCardsRickAndMorty()).thenReturn(2);
        when(pairCount.getNumberOfPairsGameOfThrones()).thenReturn(1);

        List<CardDTO> expected = new ArrayList<>();
        CardDTO cardDTO1 = new CardDTO("uuid1", 1, "Character One", "image1", "Species 1");
        CardDTO cardDTO2 = new CardDTO("uuid3", 3, "Character Three", "image3", "Species 1");
        CardDTO cardDTO3 = new CardDTO("uuid4", 4, "Character Four", "imageUrl4", "Family 1");
        CardDTO cardDTO4 = new CardDTO("uuid6", 6, "Character Six", "imageUrl6", "Family 1");
        expected.add(cardDTO1);
        expected.add(cardDTO2);
        expected.add(cardDTO3);
        expected.add(cardDTO4);

        // when
        List<CardDTO> randomCharacters = mixedCardSetService.getRandomCharacters(-1);

        // then
        verify(randMService).generateBoardByCondition(2, 1, BoardGenerationCondition.SPECIES);
        verify(goTService).getRandomCharacters(1);
        Assertions.assertThat(randomCharacters).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void testGetRandomCharacters_odd_number_of_pairs() {
        // given
        List<RandMCharacter> randMCharacters = new ArrayList<>();
        RandMCharacter character1 = new RandMCharacter("uuid1", 1, "Character One", "Species 1", "image1");
        RandMCharacter character2 = new RandMCharacter("uuid2", 2, "Character Two", "Species 2", "image2");
        RandMCharacter character3 = new RandMCharacter("uuid3", 3, "Character Three", "Species 1", "image3");
        RandMCharacter character4 = new RandMCharacter("uuid4", 4, "Character Four", "Species 2", "image4");
        randMCharacters.add(character1);
        randMCharacters.add(character2);
        randMCharacters.add(character3);
        randMCharacters.add(character4);
        List<GoTCharacter> goTCharacters = new ArrayList<>();
        GoTCharacter character5 = new GoTCharacter("uuid5", 5, "Character Five", "Family 1", "imageUrl5");
        GoTCharacter character6 = new GoTCharacter("uuid6", 6, "Character Six", "Family 1", "imageUrl6");
        goTCharacters.add(character5);
        goTCharacters.add(character6);

        Mockito.<List<RandMCharacter>>when(randMService.generateBoardByCondition(4, 1, BoardGenerationCondition.SPECIES)).thenReturn(randMCharacters);
        when(goTService.getRandomCharacters(1)).thenReturn(goTCharacters);
        when(pairCount.getNumberOfCardsRickAndMorty()).thenReturn(4); // Set the desired value
        when(pairCount.getNumberOfPairsGameOfThrones()).thenReturn(1);

        List<CardDTO> expected = new ArrayList<>();
        CardDTO cardDTO1 = new CardDTO("uuid1", 1, "Character One", "image1", "Species 1");
        CardDTO cardDTO2 = new CardDTO("uuid2", 2, "Character Two", "image2", "Species 2");
        CardDTO cardDTO3 = new CardDTO("uuid3", 3, "Character Three", "image3", "Species 1");
        CardDTO cardDTO4 = new CardDTO("uuid4", 4, "Character Four", "image4", "Species 2");
        CardDTO cardDTO5 = new CardDTO("uuid5", 5, "Character Five", "imageUrl5", "Family 1");
        CardDTO cardDTO6 = new CardDTO("uuid6", 6, "Character Six", "imageUrl6", "Family 1");
        expected.add(cardDTO1);
        expected.add(cardDTO2);
        expected.add(cardDTO3);
        expected.add(cardDTO4);
        expected.add(cardDTO5);
        expected.add(cardDTO6);

        // when
        List<CardDTO> randomCharacters = mixedCardSetService.getRandomCharacters(3);

        // then
        verify(randMService).generateBoardByCondition(4, 1, BoardGenerationCondition.SPECIES); // m==4 or m==6
        verify(goTService).getRandomCharacters(1); // numberOfCharacterPairs == 1 or numberOfCharacterPairs == 2
        Assertions.assertThat(randomCharacters).containsExactlyInAnyOrderElementsOf(expected);
    }

}