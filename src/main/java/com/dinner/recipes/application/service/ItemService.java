package com.dinner.recipes.application.service;

import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.Item;

public interface ItemService {

    DinnerRecipe addItem(final String recipeId, final Item item);
    DinnerRecipe update(final String recipeId, final String id, final Item item);
    DinnerRecipe removeItem(final String recipeId, final String id);
    Item get(final String id);
}