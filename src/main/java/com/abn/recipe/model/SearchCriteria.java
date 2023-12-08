package com.abn.recipe.model;

import com.abn.recipe.entity.DishType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchCriteria {
    private DishType dishType;
    private Long servings;
    private List<String> excludeIngredients;
    private List<String> includeIngredients;
    private String instructions;
}
