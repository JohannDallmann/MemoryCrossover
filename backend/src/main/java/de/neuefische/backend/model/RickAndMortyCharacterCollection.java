package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RickAndMortyCharacterCollection {

    private RickAndMortyCharacterCollectionInfo info;
    private List<RandMCharacter> results;
}