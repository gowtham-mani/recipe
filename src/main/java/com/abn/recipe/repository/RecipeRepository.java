package com.abn.recipe.repository;

import com.abn.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByNameEqualsIgnoreCase(String recipeName);
}
