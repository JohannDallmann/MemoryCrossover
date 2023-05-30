package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import de.neuefische.backend.repository.RandMCharacterWithNamePrefixIntersectionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
    private final RandMCharacterWithNamePrefixIntersectionRepository RandMCharNamePrefixIntersectionRepo;

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


    public List<RandMCharacter> getSamplePairingForUniqueSpecies(int m, int n) {
        List<GroupBySpecies> allCharactersGroups;
        allCharactersGroups = randMRepo.findRandomPairsBySpecies();

        int boardSize = m * n;
        int numberOfPairs = boardSize/2;

        if (numberOfPairs > allCharactersGroups.size()){
            numberOfPairs = allCharactersGroups.size();
        }
        Collections.shuffle(allCharactersGroups);

        ArrayList<RandMCharacter> RandomPairsBySpecies = new ArrayList<>();


        for (GroupBySpecies groupBySpecies : allCharactersGroups.subList(0, numberOfPairs)) {
            RandMCharacter[] charactersInGroup = groupBySpecies.getCharacters();
            List<RandMCharacter> charactersInGroupList = Arrays.asList(charactersInGroup);
            Collections.shuffle(charactersInGroupList);

            for (int j = 0; j < 2; j++) {
                RandomPairsBySpecies.add(charactersInGroupList.get(j));
            }
        }
        Collections.shuffle(RandomPairsBySpecies);

        return RandomPairsBySpecies;
    }

    public List<RandMCharacterWithNamePrefix> getSamplePairingForSameNamePrefix(int m, int n) {
        int boardSize = m * n;
        int numberOfPairs = boardSize/2;

        List<RandMCharacterWithNamePrefixIntersection> RandomPairs;
        RandomPairs = RandMCharNamePrefixIntersectionRepo.findAll();

        if (numberOfPairs > RandomPairs.size()){
            numberOfPairs = RandomPairs.size();
        }

        ArrayList<RandMCharacterWithNamePrefix> RandomWithNamePrefix = new ArrayList<>();

        Collections.shuffle(RandomPairs);


        for (int i = 0; i < numberOfPairs; i++) {
            RandomWithNamePrefix.add(RandomPairs.get(i).getIntersection()[0]);

            RandMCharacterWithNamePrefix prefixObject = RandMCharacterWithNamePrefix.fromIntersection(RandomPairs.get(i));
            RandomWithNamePrefix.add(prefixObject);
        }

        Collections.shuffle(RandomWithNamePrefix);

        return RandomWithNamePrefix;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> generateBoardByCondition(int m, int n, int condition) {

        if (condition == 1){
            return (List<T>) getSamplePairingForUniqueSpecies(m,n);
        } else if (condition == 2) {
            return (List<T>) getSamplePairingForSameNamePrefix(m,n);

        } else return (List<T>) getSamplePairing(m,n);
    }

}
