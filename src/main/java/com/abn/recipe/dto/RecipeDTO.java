package com.abn.recipe.dto;

import com.abn.recipe.entity.DishType;
import com.abn.recipe.entity.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class RecipeDTO {
    private String name;
    private DishType type;
    private Set<Ingredient> ingredients;
    private String description;
    private int servings;
    private String instructions;
}
