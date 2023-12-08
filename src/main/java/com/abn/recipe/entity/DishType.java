package com.abn.recipe.entity;

import com.abn.recipe.exception.IncorrectDishTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;

import static java.util.Arrays.stream;

public enum DishType {
    VEGETARIAN("vegetarian"),
    NON_VEGETARIAN("nonvegetarian");

    private final String value;

    DishType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DishType fromValue(String value) {
        return stream(values()).filter(e -> e.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IncorrectDishTypeException("Incorrect Type provided"));
    }
}
