package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@Data
public class RandMRepo {
    private Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();

    public List<RandMCharacter> getAllCharacters() {
        return new ArrayList<>(randMCharacterMap.values());
    }

    public RandMCharacter addCharacter(RandMCharacter character) {
        randMCharacterMap.put(String.valueOf(character.getId()), character);
        return randMCharacterMap.get(String.valueOf(character.getId()));
    }

}
