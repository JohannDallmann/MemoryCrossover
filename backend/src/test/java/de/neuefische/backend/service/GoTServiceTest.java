package de.neuefische.backend.service;

import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.repository.GoTRepo;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

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
    }
}