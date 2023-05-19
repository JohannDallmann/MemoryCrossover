package de.neuefische.backend.repositoryTest;

import de.neuefische.backend.model.RandMCharacter;
import de.neuefische.backend.repository.RandMRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RandMRepoTest {

    @Test
    void getAllCharacters_returnListWithOneEntrance() {
        //given
        RandMCharacter randMCharacter = new RandMCharacter("testuuid", 1, "testname", "testspecies", "testimage");
        Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();
        randMCharacterMap.put(randMCharacter.getUuid(),randMCharacter);
        RandMRepo randMRepo = new RandMRepo(randMCharacterMap);

        List<RandMCharacter> expectedList = new ArrayList<>();
        expectedList.add(randMCharacter);

        // when
        List<RandMCharacter> actualList = randMRepo.getAllCharacters();

        // then
        assertEquals(expectedList,actualList);
    }

    @Test
    void getAllCharacters_returnEmptyList() {
        //given
        Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();
        RandMRepo randMRepo = new RandMRepo(randMCharacterMap);

        List<RandMCharacter> expectedList = new ArrayList<>();

        // when
        List<RandMCharacter> actualList = randMRepo.getAllCharacters();

        // then
        assertEquals(expectedList,actualList);
    }

    @Test
    void addCharacter_returnSameCharacterAsInput() {
        //given
        RandMCharacter randMCharacter = new RandMCharacter("testuuid", 1, "testname", "testspecies", "testimage");
        Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();
        RandMRepo randMRepo = new RandMRepo(randMCharacterMap);

        // when
        RandMCharacter actualCharacter = randMRepo.addCharacter(randMCharacter);

        // then
        assertEquals(randMCharacter,actualCharacter);
    }
}