package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("rickAndMortyCharacters")
public class RandMCharacter {
    @Id
    private String uuid;
    private int id;
    private String name;
    private String species;
    private String image;

}
