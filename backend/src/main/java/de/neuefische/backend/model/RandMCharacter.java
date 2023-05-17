package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RandMCharacter {
    private String uuid;
    private int id;
    private String name;
    private String species;
    private String image;
}
