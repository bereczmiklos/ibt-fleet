package com.example.fleet.model;

public enum BrandName {
    MERCEDES("MERCEDES", "Mercedes"),
    FORD("FORD", "Ford"),
    TOYOTA("TOYOTA", "Toyota"),
    VOLKSWAGEN("VOLKSWAGEN", "Volkswagen");

    private final String enumType;
    private final String name;

    BrandName(String enumType, String name) {
        this.enumType = enumType;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
