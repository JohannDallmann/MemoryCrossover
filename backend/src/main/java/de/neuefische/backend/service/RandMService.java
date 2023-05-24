package de.neuefische.backend.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import de.neuefische.backend.model.RandMCharacter;
import de.neuefische.backend.model.RickAndMortyCharacterCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import de.neuefische.backend.repository.RandMRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RandMService {
    private final RandMRepo randMRepo;
    private final GenerateUUIDService generateUUIDService;

    WebClient webClient;

    @Value("${rickandmorty.url}")
    private String url;

    @PostConstruct
    public void initializeWebClient() {
        this.webClient = WebClient.create(url);
    }

    public List<RandMCharacter> getAllCharacters() {

        if (randMRepo.findAll().isEmpty()) {
            return fillCharactersFromApi();
        } else {
            return randMRepo.findAll();
        }

    }


    public List<RandMCharacter> fillCharactersFromApi() {
        List<RandMCharacter> allCharacters = new ArrayList<>();
        int page = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            String uri = "/?page=" + page;
            RickAndMortyCharacterCollection response = Objects.requireNonNull(webClient.get()
                            .uri(uri)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .retrieve()
                            .toEntity(RickAndMortyCharacterCollection.class)
                            .block())
                    .getBody();

            if(response != null) {
                List<RandMCharacter> characters = response.getResults();
                allCharacters.addAll(characters);

                hasNextPage = response.getInfo().getNext() != null;
                if (hasNextPage) {
                    page++;
                }
            }

        }

        for (RandMCharacter character : allCharacters) {
            character.setUuid(generateUUIDService.generateUUID());
        }
        randMRepo.saveAll(allCharacters);

        return allCharacters;
    }
}
