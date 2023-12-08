package com.abn.recipe.exception;

public class IncorrectDishTypeException extends RuntimeException {
    public IncorrectDishTypeException(String exception) {
        super(exception);
    }
}
