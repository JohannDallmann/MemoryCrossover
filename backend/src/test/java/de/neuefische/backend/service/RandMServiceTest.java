package de.neuefische.backend.service;

import de.neuefische.backend.model.GroupBySpecies;
import de.neuefische.backend.model.RandMCharacter;
import de.neuefische.backend.model.RandMCharacterWithNamePrefix;
import de.neuefische.backend.model.RandMCharacterWithNamePrefixIntersection;
import de.neuefische.backend.repository.RandMCharacterWithNamePrefixIntersectionRepository;
import de.neuefische.backend.repository.RandMRepo;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RandMServiceTest {

    RandMRepo randMRepo = mock(RandMRepo.class);
    GenerateUUIDService generateUUIDService = mock(GenerateUUIDService.class);
    RandMCharacterWithNamePrefixIntersectionRepository RandMCharNamePrefixIntersectionRepo = mock(RandMCharacterWithNamePrefixIntersectionRepository.class);
    MongoTemplate mongoTemplate = mock(MongoTemplate.class);

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
        assertEquals(characters, allCharacters);
        verify(randMRepo, times(2)).findAll();
    }

    @Test
    void testGetSamplePairing() {

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

    @Test
    void testGetSamplePairingForUniqueSpecies() {
        int m = 4;
        int n = 4;
        int datasetSize = m*n;

        List<GroupBySpecies> testData = createTestDatasetWithUniqueSpecies(datasetSize);

        when(randMRepo.findRandomPairsBySpecies()).thenReturn(testData);

        List<RandMCharacter> result = randMService.getSamplePairingForUniqueSpecies(m, n);

        Set<String> uniqueSpecies = new HashSet<>();
        for (RandMCharacter character : result) {
            uniqueSpecies.add(character.getSpecies());
        }

        // Assert the results
        assertEquals(datasetSize, result.size());
        // Verify that all pairs have unique species
        assertEquals(result.size() / 2, uniqueSpecies.size());

        testGetSamplePairingForUniqueSpecies_ShouldReturnDifferentOrder(m,n);
    }

    void testGetSamplePairingForUniqueSpecies_ShouldReturnDifferentOrder(int m, int n) {
        // Call the function twice
        List<RandMCharacter> result1 = randMService.getSamplePairingForUniqueSpecies(m, n);
        List<RandMCharacter> result2 = randMService.getSamplePairingForUniqueSpecies(m, n);

        // Ensure that the two results are not equal
        assertNotEquals(result1, result2);
    }

    private List<GroupBySpecies> createTestDatasetWithUniqueSpecies(int datasetSize) {

        List<GroupBySpecies> testData = new ArrayList<>();
        for (int i = 0; i < datasetSize; i++) {
            GroupBySpecies group = new GroupBySpecies();
            group.setSpecies("species_" + i);
            List<RandMCharacter> characterList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                RandMCharacter character = new RandMCharacter();
                character.setUuid(i + Integer.toString(j));
                character.setSpecies("species_" + i);
                characterList.add(character);
            }
            group.setCharacters(characterList.toArray(new RandMCharacter[0]));

            testData.add(group);
        }
        return testData;
    }

    @Test
    void testGetSamplePairingForSameNamePrefix() {

        int m = 4;
        int n = 4;
        int datasetSize = m * n;

        List<RandMCharacterWithNamePrefixIntersection> testData = createTestDatasetWithSameNamePrefix(datasetSize);

        when(RandMCharNamePrefixIntersectionRepo.findAll()).thenReturn(testData);

        List<RandMCharacterWithNamePrefix> result = randMService.getSamplePairingForSameNamePrefix(m, n);

        assertEquals(datasetSize, result.size());

    }

    private List<RandMCharacterWithNamePrefixIntersection> createTestDatasetWithSameNamePrefix(int datasetSize) {

        List<RandMCharacterWithNamePrefixIntersection> testData = new ArrayList<>();

        for (int i = 0; i < datasetSize; i++) {

            RandMCharacterWithNamePrefixIntersection intersection = new RandMCharacterWithNamePrefixIntersection();
            intersection.setName("Rick" + i);
            intersection.setName_prefix(Integer.toString(i));

            RandMCharacterWithNamePrefix intersectionAttachment = new RandMCharacterWithNamePrefix();
            intersectionAttachment.setName("Morty" + i);
            intersectionAttachment.setName_prefix(Integer.toString(i));

            RandMCharacterWithNamePrefix[] intersectionAttachmentArray = new RandMCharacterWithNamePrefix[1];
            intersectionAttachmentArray[0] = intersectionAttachment;

            intersection.setIntersection(intersectionAttachmentArray);

            testData.add(intersection);
        }

        return testData;
    }
}