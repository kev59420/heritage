package com.example.heritage.kevin.enums;

public enum CategorieProduit {
    Informatique("Informatique"),
    Peripheriques("Peripheriques"),
    Ecrans("Ecrans"),
    Audio("Audio");

    private String value;

    CategorieProduit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
