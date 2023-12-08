package com.abn.recipe.exception;

public class RecipeExistsException extends RuntimeException {
    public RecipeExistsException(String message) {
        super(message);
    }
}
