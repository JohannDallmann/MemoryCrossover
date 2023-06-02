package de.neuefische.backend.service;

import de.neuefische.backend.dto.CardDTO;
import de.neuefische.backend.model.BoardGenerationCondition;
import de.neuefische.backend.model.GoTCharacter;
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

    public List<CardDTO> getRandomCharacters(int numberOfPairs) {
        List<RandMCharacter> randMCharacters = randMService.generateBoardByCondition(2, 3, BoardGenerationCondition.DEFAULT);
        List<GoTCharacter> goTCharacters = goTService.getRandomCharacters(3);

        List<CardDTO> cardDTOList = new ArrayList<>();

        // RandMCharacters in CardDTO umwandeln und zur cardDTOList hinzufügen
        for (RandMCharacter randMCharacter : randMCharacters) {
            CardDTO cardDTO = new CardDTO();
            cardDTO.setUuid(randMCharacter.getUuid());
            cardDTO.setId(randMCharacter.getId());
            cardDTO.setName(randMCharacter.getName());
            cardDTO.setImage(randMCharacter.getImage());
            //cardDTO.setHidden(false);
            //cardDTO.setComparison(randMCharacter.getSpecies());
            cardDTOList.add(cardDTO);
        }

        // GoTCharacters in CardDTO umwandeln und zur cardDTOList hinzufügen
        for (GoTCharacter goTCharacter : goTCharacters) {
            CardDTO cardDTO = new CardDTO();
            cardDTO.setUuid(goTCharacter.getUuid());
            cardDTO.setId(goTCharacter.getId());
            cardDTO.setName(goTCharacter.getFullName());
            cardDTO.setImage(goTCharacter.getImageUrl());
            //cardDTO.setHidden(false);
            //cardDTO.setComparison(goTCharacter.getFamily());
            cardDTOList.add(cardDTO);
        }

        // Zufällige Kartenpaare auswählen
        Collections.shuffle(cardDTOList);
        cardDTOList = cardDTOList.subList(0, Math.min(cardDTOList.size(), numberOfPairs * 2));

        return cardDTOList;
    }
}
