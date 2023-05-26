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

import java.util.List;
import java.util.Objects;

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
        goTRepo.saveAll(characters);

        return characters;
    }

}
