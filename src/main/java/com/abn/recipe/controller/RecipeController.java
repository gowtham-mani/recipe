package com.abn.recipe.controller;

import com.abn.recipe.dto.RecipeDTO;
import com.abn.recipe.exception.RecipeNotFoundException;
import com.abn.recipe.model.RecipePayload;
import com.abn.recipe.model.SearchCriteria;
import com.abn.recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Get all the recipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all the recipes",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDTO.class))})
    })
    @GetMapping("/recipe/all")
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        log.debug("fetching recipes");
        List<RecipeDTO> recipes = recipeService.fetchAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @Operation(summary = "Get the recipe corresponding to the provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a recipes",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDTO.class))})
    })
    @GetMapping("/recipe/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {
        log.debug(String.format("fetching the recipe of id: $s", id));
        RecipeDTO recipeDTO = recipeService.getRecipe(id);
        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create a recipe corresponding with the provided value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a recipes",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDTO.class))})
    })
    @PostMapping("/recipe")
    public ResponseEntity<RecipeDTO> saveRecipe(@RequestBody RecipePayload recipe) {
        RecipeDTO recipeDTO = recipeService.saveRecipe(recipe);
        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update a recipe corresponding with the provided value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a recipes",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDTO.class))})
    })
    @PutMapping("/recipe")
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipePayload recipe) {
        RecipeDTO recipeDTO = recipeService.updateRecipe(recipe);
        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete a recipe corresponding to the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the recipe",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDTO.class))})
    })
    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        log.debug(String.format("Deleting the recipe with id: %s", id));
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>("Deleted recipe with id: " + id, HttpStatus.OK);
    }

    @Operation(summary = "Filter all recipes corresponding to the values provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filter recipe",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDTO.class))})
    })
    @PostMapping("/recipe/search")
    public ResponseEntity<List<RecipeDTO>> filterRecipe(@RequestBody SearchCriteria searchCriteria) {
        log.debug("searching the recipe for the provided values");
        List<RecipeDTO> recipes = recipeService.searchRecipes(searchCriteria);
        if (recipes.size() < 1) {
            throw new RecipeNotFoundException("No Recipes found matching the query");
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

}
