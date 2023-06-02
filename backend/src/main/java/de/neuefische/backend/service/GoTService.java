package de.neuefische.backend.service;

import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.repository.GoTRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoTService {
    private final GoTRepo goTRepo;
    private final GenerateUUIDService generateUUIDService;

    WebClient webClient;

    @Value("${gameofthrones.url}")
    private String url;

    @PostConstruct
    public void initializeWebClient() {
        this.webClient = WebClient.create(url);
    }

    public List<GoTCharacter> getAllCharacters() {

        if (goTRepo.findAll().isEmpty()) {
            return fillCharactersFromApi();
        } else {
            return goTRepo.findAll();
        }
    }

    public List<GoTCharacter> fillCharactersFromApi() {

        List<GoTCharacter> characters = Objects.requireNonNull(webClient.get()
                        .uri(url)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntityList(GoTCharacter.class)
                        .block())
                .getBody();

        for (GoTCharacter character : characters) {
            character.setUuid(generateUUIDService.generateUUID());
        }
        goTRepo.saveAll(Objects.requireNonNull(characters));

        return characters;
    }

    public List<GoTCharacter> getRandomCharacters(Integer numberOfCharacterPairs) {
        List<GoTCharacter> allCharacters = goTRepo.findAll();

        Map<String, List<GoTCharacter>> charactersByFamily = allCharacters.stream()
                .collect(Collectors.groupingBy(GoTCharacter::getFamily));

        List<GoTCharacter> randomCharacters = new ArrayList<>();
        int pairsCount = 0;

        for (List<GoTCharacter> characters : charactersByFamily.values()) {
            Collections.shuffle(characters);
            int pairSize = Math.min(1, characters.size() / 2);

            for (int i = 0; i < pairSize * 2 && pairsCount < numberOfCharacterPairs * 2; i++) {
                randomCharacters.add(characters.get(i));
                pairsCount++;
            }

            if (pairsCount >= numberOfCharacterPairs * 2) {
                break;
            }
        }

        return randomCharacters;
    }

}
