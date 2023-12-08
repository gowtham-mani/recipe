package com.abn.recipe.service;

import com.abn.recipe.dto.RecipeDTO;
import com.abn.recipe.entity.DishType;
import com.abn.recipe.entity.Recipe;
import com.abn.recipe.entity.Recipe_;
import com.abn.recipe.exception.RecipeExistsException;
import com.abn.recipe.exception.RecipeNotFoundException;
import com.abn.recipe.model.RecipePayload;
import com.abn.recipe.model.SearchCriteria;
import com.abn.recipe.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final EntityManager entityManager;

    public RecipeServiceImpl(RecipeRepository recipeRepository, EntityManager entityManager) {
        this.recipeRepository = recipeRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<RecipeDTO> fetchAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeRepository.findAll().stream()
                .map(this::recipeMapper)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDTO getRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("Recipe not found"));
        return recipeMapper(recipe);
    }

    @Override
    @Transactional
    public RecipeDTO saveRecipe(RecipePayload recipePayload) {
        Recipe savedRecipe = recipeRepository.findByNameEqualsIgnoreCase(recipePayload.getName());
        if (savedRecipe != null) {
            throw new RecipeExistsException("Recipe exists");
        }
        Recipe recipe = Recipe.builder()
                .name(recipePayload.getName())
                .type(recipePayload.getDishType())
                .servings(recipePayload.getServings())
                .ingredientSet(recipePayload.getIngredients())
                .description(recipePayload.getDescription())
                .instructions(recipePayload.getInstructions())
                .build();

        return recipeMapper(recipeRepository.save(recipe));

    }

    @Override
    public RecipeDTO updateRecipe(RecipePayload recipePayload) {
        Recipe savedRecipe = recipeRepository.findByNameEqualsIgnoreCase(recipePayload.getName());
        if (savedRecipe == null) {
            throw new RecipeNotFoundException("No recipe with the id");
        } else {
            return recipeMapper(update(savedRecipe, recipePayload));
        }
    }

    protected Recipe update(Recipe oldRecipe, RecipePayload newRecipe) {
        oldRecipe.setDescription(newRecipe.getDescription());
        oldRecipe.setType(newRecipe.getDishType());
        oldRecipe.setInstructions(newRecipe.getInstructions());
        oldRecipe.setServings(newRecipe.getServings());
        oldRecipe.getIngredientSet().clear();
        oldRecipe.getIngredientSet().addAll(newRecipe.getIngredients());
        return recipeRepository.save(oldRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.findById(id).ifPresentOrElse(recipeRepository::delete,
                () -> {
                    throw new RecipeNotFoundException(String.format("No Recipe with the provided id: %s exists", id));
                });
    }

    @Override
    public List<RecipeDTO> searchRecipes(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.getDishType().equals(DishType.VEGETARIAN)) {
            predicates.add(criteriaBuilder.equal(recipeRoot.get(Recipe_.TYPE), searchCriteria.getDishType()));
        }
        if (searchCriteria.getDishType().equals(DishType.NON_VEGETARIAN)) {
            predicates.add(criteriaBuilder.equal(recipeRoot.get(Recipe_.TYPE), searchCriteria.getDishType()));
        }
        if (searchCriteria.getServings() != null) {
            predicates.add(criteriaBuilder.equal(recipeRoot.get(Recipe_.SERVINGS), searchCriteria.getServings()));
        }
        if (searchCriteria.getIncludeIngredients() != null) {
            predicates.add(criteriaBuilder.isMember(searchCriteria.getIncludeIngredients(), recipeRoot.get(Recipe_.INGREDIENT_SET)));
        }
        if (searchCriteria.getExcludeIngredients() != null) {
            predicates.add(criteriaBuilder.isNotMember(searchCriteria.getExcludeIngredients(), recipeRoot.get(Recipe_.INGREDIENT_SET)));
        }
        if (searchCriteria.getInstructions() != null) {
            predicates.add((criteriaBuilder.like(recipeRoot.get(searchCriteria.getInstructions()), recipeRoot.get(Recipe_.INSTRUCTIONS))));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(criteriaQuery).getResultList().stream()
                .map(this::recipeMapper)
                .collect(Collectors.toList());
    }

    private RecipeDTO recipeMapper(Recipe recipe) {
        return RecipeDTO.builder()
                .name(recipe.getName())
                .type(recipe.getType())
                .servings(recipe.getServings())
                .description(recipe.getDescription())
                .ingredients(recipe.getIngredientSet())
                .instructions(recipe.getInstructions())
                .build();
    }
}
