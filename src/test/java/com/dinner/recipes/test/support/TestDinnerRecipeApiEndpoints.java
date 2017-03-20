package com.dinner.recipes.test.support;


import com.dinner.recipes.application.api.resource.DinnerRecipePageResponse;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class TestDinnerRecipeApiEndpoints {

    private static final String BASE_URL = "http://127.0.0.1:8080/";
    private static final String DINNER_RECIPE_BASE_URL = BASE_URL + "/api/v1/dinner-recipes";

    public static ResponseEntity<DinnerRecipeResource> create(final DinnerRecipeResource request) {
        return new TestRestTemplate().postForEntity(DINNER_RECIPE_BASE_URL, request, DinnerRecipeResource.class);
    }

    public static void update(final String recipeId, final DinnerRecipeResource request) {
        new TestRestTemplate().put(DINNER_RECIPE_BASE_URL + "/" + recipeId, request);
    }

    public static ResponseEntity<DinnerRecipeResource> find(final String recipeId) {
        return new TestRestTemplate().getForEntity(DINNER_RECIPE_BASE_URL + "/" + recipeId, DinnerRecipeResource.class);
    }

    public static ResponseEntity<DinnerRecipePageResponse> list() {
        return new TestRestTemplate().getForEntity(DINNER_RECIPE_BASE_URL, DinnerRecipePageResponse.class);
    }

    public static void delete(final String recipeId) {
        new TestRestTemplate().delete(DINNER_RECIPE_BASE_URL + "/" + recipeId, Collections.EMPTY_MAP);
    }

    public static void deleteAll() {
        new TestRestTemplate().delete(DINNER_RECIPE_BASE_URL, Collections.EMPTY_MAP);
    }

}