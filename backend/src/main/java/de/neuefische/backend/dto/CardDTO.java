package de.neuefische.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String uuid;
    private int id;
    private String name;
    private String image;
    //private boolean hidden;
    //private String comparison;
}