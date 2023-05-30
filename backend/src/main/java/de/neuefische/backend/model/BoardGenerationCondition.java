package de.neuefische.backend.model;

public enum BoardGenerationCondition {

    SPECIES("SPECIES"),
    NAME_PREFIX("NAME_PREFIX"),
    DEFAULT("DEFAULT");

    private String condition;

    BoardGenerationCondition(String s) {
        this.condition = s;
    }
}
