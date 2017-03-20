package com.dinner.recipes.application.service;

import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.Ingredient;

public interface IngredientService {

    DinnerRecipe addIngredient(final String recipeId, final String itemId, final Ingredient ingredient);
    DinnerRecipe removeIngredient(final String recipeId, final String itemId, final Ingredient ingredient);

}
