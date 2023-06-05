package de.neuefische.backend.service;

import de.neuefische.backend.dto.CardDTO;
import de.neuefische.backend.model.BoardGenerationCondition;
import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.model.PairCount;
import de.neuefische.backend.model.RandMCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MixedCardSetService {
    private final RandMService randMService;
    private final GoTService goTService;
    private final PairCount pairCount;

    public List<CardDTO> getRandomCharacters(int numberOfPairs) {
        // generate at least 2 pairs
        numberOfPairs = Math.max(numberOfPairs, 2);

        pairCount.calculatePairCount(numberOfPairs);
        int numberOfCardsRickAndMorty = pairCount.getNumberOfCardsRickAndMorty();
        int numberOfPairsGameOfThrones = pairCount.getNumberOfPairsGameOfThrones();

        List<RandMCharacter> randMCharacters = randMService.generateBoardByCondition(numberOfCardsRickAndMorty, 1, BoardGenerationCondition.SPECIES);
        List<GoTCharacter> goTCharacters = goTService.getRandomCharacters(numberOfPairsGameOfThrones);

        List<CardDTO> cardDTOList = new ArrayList<>();

        for (RandMCharacter randMCharacter : randMCharacters) {
            CardDTO cardDTO = convertRandMCharacterToCardDTO(randMCharacter);
            cardDTOList.add(cardDTO);
        }

        for (GoTCharacter goTCharacter : goTCharacters) {
            CardDTO cardDTO = convertGoTCharacterToCardDTO(goTCharacter);
            cardDTOList.add(cardDTO);
        }

        Collections.shuffle(cardDTOList);
        cardDTOList = cardDTOList.subList(0, Math.min(cardDTOList.size(), numberOfPairs * 2));

        return cardDTOList;
    }

    private CardDTO convertRandMCharacterToCardDTO(RandMCharacter randMCharacter) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setUuid(randMCharacter.getUuid());
        cardDTO.setId(randMCharacter.getId());
        cardDTO.setName(randMCharacter.getName());
        cardDTO.setImage(randMCharacter.getImage());
        cardDTO.setComparison(randMCharacter.getSpecies());
        return cardDTO;
    }

    private CardDTO convertGoTCharacterToCardDTO(GoTCharacter goTCharacter) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setUuid(goTCharacter.getUuid());
        cardDTO.setId(goTCharacter.getId());
        cardDTO.setName(goTCharacter.getFullName());
        cardDTO.setImage(goTCharacter.getImageUrl());
        cardDTO.setComparison(goTCharacter.getFamily());
        return cardDTO;
    }
}
