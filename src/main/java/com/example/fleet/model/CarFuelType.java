package com.example.fleet.model;

public enum CarFuelType {
    //ELECTRIC,DIESEL,PETROL
    ELECTRIC("ELECTRIC", "elektromos"),
    DIESEL("DIESEL", "dízel"),
    PETROL("PETROL", "benzin");

    private final String enumType;
    private final String name;

    CarFuelType(String enumType, String name) {
        this.enumType = enumType;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
