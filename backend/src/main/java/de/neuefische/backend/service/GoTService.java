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
        List<List<GoTCharacter>> characterListsByFamily = new ArrayList<>(charactersByFamily.values());
        Collections.shuffle(characterListsByFamily);


        for (List<GoTCharacter> familyMemberList : characterListsByFamily) {
            Collections.shuffle(familyMemberList);

            if(familyMemberList.size() < 2)
                continue;

            for (int i = 0; i < 2 ; i++) {
                randomCharacters.add(familyMemberList.get(i));
            }

            if (randomCharacters.size() >= numberOfCharacterPairs * 2) {
                break;
            }
        }

        return randomCharacters;
    }

}
