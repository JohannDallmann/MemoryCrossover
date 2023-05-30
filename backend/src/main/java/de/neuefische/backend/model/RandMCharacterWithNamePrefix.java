package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandMCharacterWithNamePrefix extends RandMCharacter {
    private String name_prefix;

    public static RandMCharacterWithNamePrefix fromIntersection(RandMCharacterWithNamePrefixIntersection object) {
        RandMCharacterWithNamePrefix character = new RandMCharacterWithNamePrefix();
        character.setUuid(object.getUuid());
        character.setId(object.getId());
        character.setName(object.getName());
        character.setSpecies(object.getSpecies());
        character.setImage(object.getImage());
        character.setName_prefix(object.getName_prefix());

        return character;
    }
}