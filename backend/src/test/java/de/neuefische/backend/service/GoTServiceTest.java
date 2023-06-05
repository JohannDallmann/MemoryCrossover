package de.neuefische.backend.service;

import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.repository.GoTRepo;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GoTServiceTest {

    GoTRepo goTRepo = mock(GoTRepo.class);
    GenerateUUIDService generateUUIDService = mock(GenerateUUIDService.class);
    WebClient webClient = mock(WebClient.class);

    GoTService goTService = new GoTService(goTRepo, generateUUIDService);

    @Test
    void testGetAllCharacters_when_character_list_is_not_empty_then_no_request_to_api_and_repo_is_called() {
        // given
        List<GoTCharacter> characters = new ArrayList<>();
        GoTCharacter character1 = new GoTCharacter();
        characters.add(character1);
        when(goTRepo.findAll()).thenReturn(characters);

        // when
        List<GoTCharacter> actual = goTService.getAllCharacters();

        // then
        verify(goTRepo, times(2)).findAll();
        assertEquals(characters, actual);
    }

    @Test
    void testGetRandomCharacters_quantity_one_delivers_one_pair_of_got_characters() {
        // given
        List<GoTCharacter> characters = new ArrayList<>();
        GoTCharacter character1 = new GoTCharacter("uuid1", 1, "Character One", "Familie 1", "imageUrl1");
        GoTCharacter character2 = new GoTCharacter("uuid2", 2, "Character Two", "Familie 2", "imageUrl2");
        GoTCharacter character3 = new GoTCharacter("uuid3", 3, "Character Three", "Familie 1", "imageUrl3");
        characters.add(character1);
        characters.add(character2);
        characters.add(character3);
        when(goTRepo.findAll()).thenReturn(characters);
        List<GoTCharacter> expected = List.of(character1, character3);

        // when
        List<GoTCharacter> randomCharacters = goTService.getRandomCharacters(1);

        // then
        verify(goTRepo).findAll();
        verifyNoMoreInteractions(goTRepo);


        assertThat(randomCharacters, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void testGetRandomCharacters_case_empty_list_when_no_pairs_creatable() {
        // given
        List<GoTCharacter> characters = new ArrayList<>();
        GoTCharacter character1 = new GoTCharacter("uuid1", 1, "Character One", "Familie 1", "imageUrl1");
        GoTCharacter character2 = new GoTCharacter("uuid2", 2, "Character Two", "Familie 2", "imageUrl2");
        GoTCharacter character3 = new GoTCharacter("uuid3", 3, "Character Three", "Familie 3", "imageUrl3");
        characters.add(character1);
        characters.add(character2);
        characters.add(character3);
        when(goTRepo.findAll()).thenReturn(characters);
        List<GoTCharacter> expected = new ArrayList<>();

        // when
        List<GoTCharacter> randomCharacters = goTService.getRandomCharacters(1);

        // then
        verify(goTRepo).findAll();
        verifyNoMoreInteractions(goTRepo);
        assertEquals(expected, randomCharacters);
    }

    @Test
    void testGetRandomCharacters_case_quantity_parameter_is_zero() {
        // given
        List<GoTCharacter> characters = new ArrayList<>();
        GoTCharacter character1 = new GoTCharacter("uuid1", 1, "Character One", "Familie 1", "imageUrl1");
        GoTCharacter character2 = new GoTCharacter("uuid2", 2, "Character Two", "Familie 2", "imageUrl2");
        GoTCharacter character3 = new GoTCharacter("uuid3", 3, "Character Three", "Familie 3", "imageUrl3");
        characters.add(character1);
        characters.add(character2);
        characters.add(character3);
        when(goTRepo.findAll()).thenReturn(characters);
        List<GoTCharacter> expected = new ArrayList<>();

        // when
        List<GoTCharacter> randomCharacters = goTService.getRandomCharacters(0);

        // then
        verify(goTRepo).findAll();
        verifyNoMoreInteractions(goTRepo);
        assertEquals(expected, randomCharacters);
    }

    @Test
    void testGetRandomCharacters_FamiliesOccurTwice() {
        // given
        List<GoTCharacter> characters = Arrays.asList(
                new GoTCharacter("uuid1", 1, "Character One", "Familie 1", "imageUrl1"),
                new GoTCharacter("uuid2", 2, "Character Two", "Familie 2", "imageUrl2"),
                new GoTCharacter("uuid3", 3, "Character Three", "Familie 3", "imageUrl3"),
                new GoTCharacter("uuid4", 4, "Character Four", "Familie 2", "imageUrl4"),
                new GoTCharacter("uuid5", 5, "Character Five", "Familie 1", "imageUrl5")
        );
        when(goTRepo.findAll()).thenReturn(characters);

        // when
        List<GoTCharacter> randomCharacters = goTService.getRandomCharacters(2);

        // then
        verify(goTRepo).findAll();
        verifyNoMoreInteractions(goTRepo);

        assertEquals(4, randomCharacters.size());

        Map<String, Integer> familyOccurrences = new HashMap<>();

        for (GoTCharacter character : randomCharacters) {
            String family = character.getFamily();
            familyOccurrences.put(family, familyOccurrences.getOrDefault(family, 0) + 1);
        }
        for (int count : familyOccurrences.values()) {
            assertEquals(2, count);
        }
    }

    @Test
    void testGetRandomCharacters_MaxOnePairPerFamily() {
        // given
        List<GoTCharacter> characters = Arrays.asList(
                new GoTCharacter("uuid1", 1, "Character One", "Familie 1", "imageUrl1"),
                new GoTCharacter("uuid2", 2, "Character Two", "Familie 2", "imageUrl2"),
                new GoTCharacter("uuid3", 3, "Character Three", "Familie 3", "imageUrl3"),
                new GoTCharacter("uuid4", 4, "Character Four", "Familie 2", "imageUrl4"),
                new GoTCharacter("uuid5", 5, "Character Five", "Familie 1", "imageUrl5"),
                new GoTCharacter("uuid5", 6, "Character Six", "Familie 2", "imageUrl6"),
                new GoTCharacter("uuid5", 7, "Character Seven", "Familie 1", "imageUrl7"),
                new GoTCharacter("uuid5", 8, "Character Eight", "Familie 1", "imageUrl8"),
                new GoTCharacter("uuid5", 9, "Character Nine", "Familie 2", "imageUrl9")
        );
        when(goTRepo.findAll()).thenReturn(characters);

        // when
        List<GoTCharacter> randomCharacters = goTService.getRandomCharacters(10);

        // then
        Map<String, Integer> cardsPerFamily = new HashMap<>();
        for (GoTCharacter character : randomCharacters) {
            String family = character.getFamily();
            cardsPerFamily.put(family, cardsPerFamily.getOrDefault(family, 0) + 1);
        }
        for (int pairs : cardsPerFamily.values()) {
            assertTrue(pairs <= 2);
        }
    }
}