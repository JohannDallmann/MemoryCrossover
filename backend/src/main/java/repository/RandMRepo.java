package repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.RandMCharacter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RandMRepo {
    private Map<String, RandMCharacter> randMCharacterMap = new HashMap<>();



    public List<RandMCharacter> getAllCharacters() {
        return new ArrayList<>(randMCharacterMap.values());
    }
}
