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
@Document("rickAndMortyIntersection")
public class RandMCharacterWithNamePrefixIntersection extends RandMCharacterWithNamePrefix{
    private RandMCharacterWithNamePrefix[] intersection;
}
