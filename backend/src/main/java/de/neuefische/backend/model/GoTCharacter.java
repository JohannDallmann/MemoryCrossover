package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("gameOfThronesCharacters")
public class GoTCharacter {
    @Id
    private String uuid;
    private int id;
    private String fullName;
    private String family;
    private String imageUrl;

}
