package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RickAndMortyCharacterCollectionInfo {
    private int count;
    private int pages;
    private String next;
}