package com.abn.recipe.util;

import com.abn.recipe.dto.RecipeDTO;
import com.abn.recipe.entity.DishType;
import com.abn.recipe.entity.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TestUtil {

    public static List<RecipeDTO> getRecipes() {
        List<RecipeDTO> recipes = new ArrayList<>();

        Ingredient egg = new Ingredient();
        egg.setId(1L);
        egg.setName("Egg");
        egg.setQuantity("1 or 2");
        egg.setDescription("eggs");

        Ingredient salt = new Ingredient();
        egg.setId(2L);
        egg.setName("Salt");
        egg.setQuantity("As per taste");
        egg.setDescription("Table Salt");

        Ingredient oil = new Ingredient();
        egg.setId(3L);
        egg.setName("Oil");
        egg.setQuantity("As required");
        egg.setDescription("Any cooking oil");

        Ingredient water = new Ingredient();
        egg.setId(4L);
        egg.setName("Water");
        egg.setQuantity("As required");
        egg.setDescription("Tap water");

        RecipeDTO scrambledEgg = RecipeDTO.builder()
                .name("scrambled eggs")
                .type(DishType.NON_VEGETARIAN)
                .ingredients(new HashSet<>(Arrays.asList(egg, salt, oil)))
                .servings(1)
                .description("scrambled eggs for breakfast")
                .instructions("put a pan in the stove, add oil and once hot, add the eggs and salt.  " +
                        "keep stirring in low heat for couple of minutes to get a good scrambled egg")
                .build();

        RecipeDTO boiledEgg = RecipeDTO.builder()
                .name("Boiled egg")
                .type(DishType.NON_VEGETARIAN)
                .ingredients(new HashSet<>(Arrays.asList(egg, salt, water)))
                .servings(1)
                .description("Boiled egg")
                .instructions("Pour water in a vessel and add a full egg into it.  Let it boil for 8 minutes to get fully cooked egg." +
                        "Drain the water and rinse the egg with cold water to peel the shell easily.  For flavour salt/pepper can be added")
                .build();

        recipes.add(scrambledEgg);

        recipes.add(boiledEgg);

        return recipes;
    }
}
