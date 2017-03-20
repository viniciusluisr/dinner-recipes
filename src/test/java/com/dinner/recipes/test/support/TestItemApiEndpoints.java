package com.dinner.recipes.test.support;


import com.dinner.recipes.application.api.resource.DinnerRecipePageResponse;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class TestItemApiEndpoints {

    private static final String BASE_URL = "http://127.0.0.1:8080";
    private static final String DINNER_RECIPE_BASE_URL = BASE_URL + "/api/v1/dinner-recipes";

    public static ResponseEntity<DinnerRecipeResource> addItem(final String recipeId, final ItemResource request) {
        return new TestRestTemplate().postForEntity(getItemUrl(recipeId), request, DinnerRecipeResource.class);
    }

    public static void update(final String recipeId, final String itemId, final ItemResource request) {
        new TestRestTemplate().put(getItemUrl(recipeId) + "/" + itemId, request);
    }

    public static ResponseEntity<ItemResource> find(final String recipeId, final String itemId) {
        return new TestRestTemplate().getForEntity(getItemUrl(recipeId) + "/" + itemId, ItemResource.class);
    }

    public static void removeItem(final String recipeId, final String itemId) {
        new TestRestTemplate().delete(getItemUrl(recipeId) + "/" + itemId, Collections.EMPTY_MAP);
    }

    private static String getItemUrl(final String recipeId) {
        return DINNER_RECIPE_BASE_URL +
                "/" +
                recipeId +
                "/items";

    }

}