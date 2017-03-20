package com.dinner.recipes.test.support;


import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.IngredientResource;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class TestIngredientApiEndpoints {

    private static final String BASE_URL = "http://127.0.0.1:8080";
    private static final String DINNER_RECIPE_BASE_URL = BASE_URL + "/api/v1/dinner-recipes";

    public static ResponseEntity<DinnerRecipeResource> addIngredient(final String recipeId, final String itemId, final IngredientResource request) {
        return new TestRestTemplate().postForEntity(getIngredientUrl(recipeId, itemId), request, DinnerRecipeResource.class);
    }

    public static void removeIngredient(final String recipeId, final String itemId, final IngredientResource request) {
        new TestRestTemplate().put(getIngredientUrl(recipeId, itemId), request, Collections.EMPTY_MAP);
    }

    private static String getIngredientUrl(final String recipeId, final String itemId) {
        return DINNER_RECIPE_BASE_URL +
                "/" +
                recipeId +
                "/items/" +
                itemId +
                "/ingredients";

    }

}