package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import de.neuefische.backend.repository.RandMCharacterWithNamePrefixIntersectionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import de.neuefische.backend.repository.RandMRepo;

import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RandMService {
    private final RandMRepo randMRepo;
    private final GenerateUUIDService generateUUIDService;
    private final RandMCharacterWithNamePrefixIntersectionRepository randMCharNamePrefixIntersectionRepo;
    private final MongoTemplate mt;

    private static final Logger logger = Logger.getLogger(RandMService.class.getName());

    String asciiLogo = """
             
             __  __                                    ____                                      \s
            |  \\/  | ___ _ __ ___   ___  _ __ _   _   / ___|_ __ ___  ___ ___  _____   _____ _ __\s
            | |\\/| |/ _ \\ '_ ` _ \\ / _ \\| '__| | | | | |   | '__/ _ \\/ __/ __|/ _ \\ \\ / / _ \\ '__|
            | |  | |  __/ | | | | | (_) | |  | |_| | | |___| | | (_) \\__ \\__ \\ (_) \\ V /  __/ |  \s
            |_|  |_|\\___|_| |_| |_|\\___/|_|   \\__, |  \\____|_|  \\___/|___/___/\\___/ \\_/ \\___|_|  \s
                                              |___/                                              \s
            """;
    WebClient webClient;

    @Value("${rickandmorty.url}")
    private String url;

    @PostConstruct
    public void initializeWebClient() {
        this.webClient = WebClient.create(url);
    }

    public List<RandMCharacter> getAllCharacters() {

        if (randMRepo.findAll().isEmpty()) {
            logger.info(asciiLogo);
            logger.info("Loading characters into the database using the Rick and Morty API");
            List<RandMCharacter> charactersFromApi = fillCharactersFromApi();

            logger.info("Creating the necessary service data");
            runAggregationStep1("Morty");
            runAggregationStep1("Rick");
            runAggregationStep2();
            logger.info("Initialization completed");

            return charactersFromApi;

        } else {
            return randMRepo.findAll();
        }

    }

    final String REPLACEALL = "$replaceAll";
    final String INPUT = "input";

    final String REPLACEMENT = "replacement";

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


    public void runAggregationStep1(String matchRegex) {
        AggregationOperation matchStage = Aggregation.match(Criteria.where("name").regex(matchRegex, "i"));

        AddFieldsOperation addFieldsStage1 = Aggregation.addFields()
                // eslint-disable-next-line react-hooks/exhaustive-deps
                .addFieldWithValue("name_prefix",
                        new Document(REPLACEALL, new Document(INPUT, "$name")
                                .append("find", matchRegex)
                                .append(REPLACEMENT, "")))
                .build();

        AddFieldsOperation addFieldsStage2 = Aggregation.addFields()
                .addFieldWithValue("_class",
                        new Document(REPLACEALL, new Document(INPUT, "$_class")
                                .append("find", ".RandMCharacter")
                                .append(REPLACEMENT, ".RandMCharacterWithNamePrefix")))
                .build();

        OutOperation outStage = Aggregation.out(matchRegex.toLowerCase() + "Set");

        Aggregation aggregation = Aggregation.newAggregation(matchStage, addFieldsStage1, addFieldsStage2, outStage);

        mt.aggregate(aggregation, "rickAndMortyCharacters", Object.class)
                .forEach(result -> {
                    // Process aggregation results
                    // ...
                });
    }

    public void runAggregationStep2() {
        LookupOperation lookupStage = LookupOperation.newLookup()
                .from("mortySet")
                // eslint-disable-next-line react-hooks/exhaustive-deps
                .localField("name_prefix")
                // eslint-disable-next-line react-hooks/exhaustive-deps
                .foreignField("name_prefix")
                .as("intersection");

        AggregationOperation matchStage = Aggregation.match(Criteria.where("intersection")
                .not()
                .size(0));

        AddFieldsOperation addFieldsStage = Aggregation.addFields()
                .addFieldWithValue("_class",
                        new Document(REPLACEALL, new Document(INPUT, "$_class")
                                .append("find", ".RandMCharacterWithNamePrefix")
                                .append(REPLACEMENT, ".RandMCharacterWithNamePrefixIntersection")))
                .build();

        OutOperation outStage = Aggregation.out( "rickAndMortyIntersection");


        Aggregation aggregation = Aggregation.newAggregation(lookupStage, matchStage, addFieldsStage, outStage);

        mt.aggregate(aggregation,"rickSet",  Object.class)
                .forEach(result -> {
                    // Process aggregation results
                    // ...
                });
    }


    public List<RandMCharacter> getSamplePairing(int m, int n) {
        int boardSize = m * n;
        int numberOfPairs = boardSize/2;

        ArrayList<RandMCharacter> characters = new ArrayList<>(randMRepo.findAll());
        Collections.shuffle(characters);

        ArrayList<RandMCharacter> randomCharacters = new ArrayList<>();

        for (int i = 0; i < numberOfPairs; i++) {
            randomCharacters.add(characters.get(i));
            randomCharacters.add(characters.get(i));
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

        ArrayList<RandMCharacter> randomPairsBySpecies = new ArrayList<>();


        for (GroupBySpecies groupBySpecies : allCharactersGroups.subList(0, numberOfPairs)) {
            RandMCharacter[] charactersInGroup = groupBySpecies.getCharacters();
            List<RandMCharacter> charactersInGroupList = Arrays.asList(charactersInGroup);
            Collections.shuffle(charactersInGroupList);

            for (int j = 0; j < 2; j++) {
                randomPairsBySpecies.add(charactersInGroupList.get(j));
            }
        }
        Collections.shuffle(randomPairsBySpecies);

        return randomPairsBySpecies;
    }

    public List<RandMCharacterWithNamePrefix> getSamplePairingForSameNamePrefix(int m, int n) {
        int boardSize = m * n;
        int numberOfPairs = boardSize/2;

        List<RandMCharacterWithNamePrefixIntersection> randomPairs;
        randomPairs = randMCharNamePrefixIntersectionRepo.findAll();

        if (numberOfPairs > randomPairs.size()){
            numberOfPairs = randomPairs.size();
        }

        ArrayList<RandMCharacterWithNamePrefix> randomWithNamePrefix = new ArrayList<>();

        Collections.shuffle(randomPairs);


        for (int i = 0; i < numberOfPairs; i++) {
            randomWithNamePrefix.add(randomPairs.get(i).getIntersection()[0]);

            RandMCharacterWithNamePrefix prefixObject = RandMCharacterWithNamePrefix.fromIntersection(randomPairs.get(i));
            randomWithNamePrefix.add(prefixObject);
        }

        Collections.shuffle(randomWithNamePrefix);

        return randomWithNamePrefix;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> generateBoardByCondition(int m, int n, BoardGenerationCondition condition) {

        return switch (condition) {
            case SPECIES -> (List<T>) getSamplePairingForUniqueSpecies(m, n);
            case NAME_PREFIX -> (List<T>) getSamplePairingForSameNamePrefix(m, n);
            default -> (List<T>) getSamplePairing(m, n);
        };
    }

}
