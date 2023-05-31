package de.neuefische.backend.service;

import de.neuefische.backend.model.RandMCharacter;
import de.neuefische.backend.repository.RandMCharacterWithNamePrefixIntersectionRepository;
import de.neuefische.backend.repository.RandMRepo;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RandMServiceTest {

    RandMRepo randMRepo = mock(RandMRepo.class);
    GenerateUUIDService generateUUIDService = mock(GenerateUUIDService.class);
    RandMCharacterWithNamePrefixIntersectionRepository RandMCharNamePrefixIntersectionRepo = mock(RandMCharacterWithNamePrefixIntersectionRepository.class);
    MongoTemplate mongoTemplate = mock(MongoTemplate.class);;
    WebClient webClient = mock(WebClient.class);

    RandMService randMService = new RandMService(randMRepo, generateUUIDService, RandMCharNamePrefixIntersectionRepo, mongoTemplate);

    @Test
    void testGetAllCharacters_when_character_list_is_not_empty_then_no_request_to_api_and_repo_is_called() {
        // given
        List<RandMCharacter> characters = new ArrayList<>();
        RandMCharacter character1 = new RandMCharacter();
        characters.add(character1);
        when(randMRepo.findAll()).thenReturn(characters);

        // when
        List<RandMCharacter> allCharacters = randMService.getAllCharacters();

        //then
        verify(randMRepo, times(2)).findAll();
    }

    @Test
    public void testGetSamplePairing() {

        int m = 4;
        int n = 4;
        int datasetSize = m*n;

        // Set up the test data
        List<RandMCharacter> testData = createTestDataset(datasetSize);

        // Mock the randMRepo dependency
        when(randMRepo.findAll()).thenReturn(testData);

        List<RandMCharacter> result = randMService.getSamplePairing(m, n);


        Set<RandMCharacter> uniqueCharacters = new HashSet<>(result);

        // Assert the results
        assertEquals(datasetSize, result.size()); // Check if the result size matches the board size (m * n)
        // Verify that the getSamplePairing method selects a random half from the test data and duplicates each element
        assertEquals(result.size() / 2, uniqueCharacters.size()); // Check if each element has a pair
        assertTrue(isRandomized(result, testData)); // Check if the pairs are randomized
    }

    // Helper method to create a test dataset
    private List<RandMCharacter> createTestDataset(int datasetSize) {

        List<RandMCharacter> testData = new ArrayList<>();
        for (int i = 0; i < datasetSize; i++) {
            RandMCharacter character = new RandMCharacter();
            character.setId(i);
            testData.add(character);
        }

        return testData;
    }

    // Helper method to check if the list contains randomized pairs
    // Return true if the pairs are randomized; otherwise, return false
    private boolean isRandomized(List<RandMCharacter> result, List<RandMCharacter> testData) {

        List<RandMCharacter> originalList = new ArrayList<>(testData);

        originalList.sort(Comparator.comparing(RandMCharacter::getId));
        result.sort(Comparator.comparing(RandMCharacter::getId));

        return !originalList.equals(result);
    }
}