package de.neuefische.backend.service;

import de.neuefische.backend.model.Score;
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
    void testFindAll() {
        // given
        List<Score> scores = new ArrayList<>();
        when(highscoreService.findAll()).thenReturn(scores);

        // when
        List<Score> actual = highscoreService.findAll();

        // then
        verify(highscoreRepo).findAll();
        assertEquals(scores, actual);
    }

    @Test
    void findAllByOrderByScoreAsc() {
        // given
        List<Score> scores = new ArrayList<>();
        when(highscoreService.findAllByOrderByScoreAsc()).thenReturn(scores);

        // when
        List<Score> actual = highscoreService.findAllByOrderByScoreAsc();

        // then
        verify(highscoreRepo).findAllByOrderByScoreAsc();
        assertEquals(scores, actual);
    }

    @Test
    void findAllByOrderByScoreDesc() {
        // given
        List<Score> scores = new ArrayList<>();
        when(highscoreService.findAllByOrderByScoreDesc()).thenReturn(scores);

        // when
        List<Score> actual = highscoreService.findAllByOrderByScoreDesc();

        // then
        verify(highscoreRepo).findAllByOrderByScoreDesc();
        assertEquals(scores, actual);
    }

    @Test
    void findAllByOrderByTimestampAsc() {
        // given
        List<Score> scores = new ArrayList<>();
        when(highscoreService.findAllByOrderByTimestampAsc()).thenReturn(scores);

        // when
        List<Score> actual = highscoreService.findAllByOrderByTimestampAsc();

        // then
        verify(highscoreRepo).findAllByOrderByTimestampAsc();
        assertEquals(scores, actual);
    }

    @Test
    void findAllByOrderByTimestampDesc() {
        /// given
        List<Score> scores = new ArrayList<>();
        when(highscoreService.findAllByOrderByTimestampDesc()).thenReturn(scores);

        // when
        List<Score> actual = highscoreService.findAllByOrderByTimestampDesc();

        // then
        verify(highscoreRepo).findAllByOrderByTimestampDesc();
        assertEquals(scores, actual);
    }

}