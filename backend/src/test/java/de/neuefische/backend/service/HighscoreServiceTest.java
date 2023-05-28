package de.neuefische.backend.service;

import de.neuefische.backend.model.GameResult;
import de.neuefische.backend.repository.HighscoreRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HighscoreServiceTest {

    HighscoreRepo highscoreRepo = mock(HighscoreRepo.class);
    GenerateUUIDService generateUUIDService = mock(GenerateUUIDService.class);

    HighscoreService highscoreService = new HighscoreService(highscoreRepo, generateUUIDService);

    @Test
    void testSave() {
        // given
        GameResult gameResult = new GameResult();
        List<GameResult> gameResults = new ArrayList<>();
        when(highscoreRepo.findAll()).thenReturn(gameResults);

        // when
        List<GameResult> actual = highscoreService.save(gameResult);

        // then
        verify(generateUUIDService).generateUUID();
        verify(highscoreRepo).save(gameResult);
        verify(highscoreRepo).findAll();
        assertEquals(gameResults, actual);
    }

    @Test
    void testFindAll() {
        // given
        List<GameResult> gameResults = new ArrayList<>();
        when(highscoreRepo.findAll()).thenReturn(gameResults);

        // when
        List<GameResult> actual = highscoreService.findAll();

        // then
        verify(highscoreRepo).findAll();
        assertEquals(gameResults, actual);
    }

    @Test
    void testFindAllByOrderByScoreAsc() {
        // given
        List<GameResult> gameResults = new ArrayList<>();
        when(highscoreRepo.findAllByOrderByScoreAsc()).thenReturn(gameResults);

        // when
        List<GameResult> actual = highscoreService.findAllByOrderByScoreAsc();

        // then
        verify(highscoreRepo).findAllByOrderByScoreAsc();
        assertEquals(gameResults, actual);
    }

    @Test
    void testFindAllByOrderByScoreDesc() {
        // given
        List<GameResult> gameResults = new ArrayList<>();
        when(highscoreRepo.findAllByOrderByScoreDesc()).thenReturn(gameResults);

        // when
        List<GameResult> actual = highscoreService.findAllByOrderByScoreDesc();

        // then
        verify(highscoreRepo).findAllByOrderByScoreDesc();
        assertEquals(gameResults, actual);
    }

    @Test
    void testFindAllByOrderByTimestampAsc() {
        // given
        List<GameResult> gameResults = new ArrayList<>();
        when(highscoreRepo.findAllByOrderByTimestampAsc()).thenReturn(gameResults);

        // when
        List<GameResult> actual = highscoreService.findAllByOrderByTimestampAsc();

        // then
        verify(highscoreRepo).findAllByOrderByTimestampAsc();
        assertEquals(gameResults, actual);
    }

    @Test
    void testFindAllByOrderByTimestampDesc() {
        /// given
        List<GameResult> gameResults = new ArrayList<>();
        when(highscoreRepo.findAllByOrderByTimestampDesc()).thenReturn(gameResults);

        // when
        List<GameResult> actual = highscoreService.findAllByOrderByTimestampDesc();

        // then
        verify(highscoreRepo).findAllByOrderByTimestampDesc();
        assertEquals(gameResults, actual);
    }

}