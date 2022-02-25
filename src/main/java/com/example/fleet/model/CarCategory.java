package com.example.fleet.model;

public enum CarCategory {
    //CAR,VAN,MINIBUS
    CAR("CAR", "személyautó"),
    VAN("VAN", "kisteherautó"),
    MINIBUS("MINIBUS", "kisbusz");

    private final String enumType;
    private final String name;

    CarCategory(String enumType, String name) {
        this.enumType = enumType;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
