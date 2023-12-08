package com.abn.recipe.service;

import com.abn.recipe.dto.RecipeDTO;
import com.abn.recipe.model.RecipePayload;
import com.abn.recipe.model.SearchCriteria;

import java.util.List;

public interface RecipeService {

    List<RecipeDTO> fetchAllRecipes();

    RecipeDTO getRecipe(Long id);

    RecipeDTO saveRecipe(RecipePayload recipe);

    RecipeDTO updateRecipe(RecipePayload recipe);

    void deleteRecipe(Long id);

    List<RecipeDTO> searchRecipes(SearchCriteria searchCriteria);
}
