package com.abn.recipe.controller;

import com.abn.recipe.service.RecipeService;
import com.abn.recipe.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {RecipeController.class})
class RecipeControllerTest {

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRecipes() throws Exception {
        when(recipeService.fetchAllRecipes()).thenReturn(TestUtil.getRecipes());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipe/all")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}