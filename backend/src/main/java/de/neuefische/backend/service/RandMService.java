package de.neuefische.backend.service;

import de.neuefische.backend.model.GroupBySpecies;
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

import java.util.*;

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


    public List<RandMCharacter> getSamplePairing(int m, int n) {
        int boardSize = m * n;
        int numberOfPairs = boardSize/2;

        ArrayList<RandMCharacter> Characters = new ArrayList<>(randMRepo.findAll());
        Collections.shuffle(Characters);

        ArrayList<RandMCharacter> randomCharacters = new ArrayList<>();

        for (int i = 0; i < numberOfPairs; i++) {
            randomCharacters.add(Characters.get(i));
            randomCharacters.add(Characters.get(i));
        }
        Collections.shuffle(randomCharacters);

        return randomCharacters;
    }


    public List<RandMCharacter> getSamplePairingForUniqueSpecies() {
        List<GroupBySpecies> allCharactersGroups;
        allCharactersGroups = randMRepo.findRandomPairsBySpecies();

        ArrayList<RandMCharacter> RandomPairsBySpecies = new ArrayList<>();


        for (GroupBySpecies groupBySpecies : allCharactersGroups) {
            RandMCharacter[] charactersInGroup = groupBySpecies.getCharacters();
            List<RandMCharacter> charactersInGroupList = Arrays.asList(charactersInGroup);
            Collections.shuffle(charactersInGroupList);

            for (int j = 0; j < 2; j++) {
                RandomPairsBySpecies.add(charactersInGroupList.get(j));
            }
        }

        return RandomPairsBySpecies;
    }


    public List<RandMCharacter> generateBoardByCondition(int m, int n, int condition) {

        if (condition == 1){
            return getSamplePairingForUniqueSpecies();
        }
        else return getSamplePairing(m,n);
    }

}
