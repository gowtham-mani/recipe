package com.abn.recipe.model;

import com.abn.recipe.entity.DishType;
import com.abn.recipe.entity.Ingredient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipePayload {
    @NotNull(message = "Recipe name cannot be empty")
    String name;
    @NotNull(message = "Recipe description cannot be empty")
    String description;
    int servings;
    DishType dishType;
    @Size(min = 1, message = "At least one ingredient needs to be there")
    Set<Ingredient> ingredients = new HashSet<>();
    @NotNull(message = "Instructions are needed")
    String instructions;

}
