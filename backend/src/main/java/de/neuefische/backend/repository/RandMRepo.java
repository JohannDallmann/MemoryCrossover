package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacter;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Data
public class RandMRepo {
    private Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();



    public List<RandMCharacter> getAllCharacters() {
        return new ArrayList<>(randMCharacterMap.values());
    }

    public void addCharacter(RandMCharacter character) {
        randMCharacterMap.put(String.valueOf(character.getId()), character);
    }


}
