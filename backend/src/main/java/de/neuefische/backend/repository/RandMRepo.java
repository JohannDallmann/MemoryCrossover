package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

@Repository
@AllArgsConstructor
@Data
public class RandMRepo {
    private Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();

    public List<RandMCharacter> getAllCharacters() {
        return new ArrayList<>(randMCharacterMap.values());
    }


    public List<RandMCharacter> getNRandomCharacters(int n) {

        ArrayList<RandMCharacter> Characters = new ArrayList<>(randMCharacterMap.values());
        Collections.shuffle(Characters);

        ArrayList<RandMCharacter> randomCharacters = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            randomCharacters.add(Characters.get(i));
        }

        return randomCharacters;
    }


    public RandMCharacter addCharacter(RandMCharacter character) {
        randMCharacterMap.put(String.valueOf(character.getId()), character);
        return randMCharacterMap.get(String.valueOf(character.getId()));
    }

}
