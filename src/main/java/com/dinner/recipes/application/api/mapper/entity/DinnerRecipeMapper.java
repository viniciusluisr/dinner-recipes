package com.dinner.recipes.application.api.mapper.entity;

import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.Item;
import com.dinner.recipes.infra.mapper.Mapper;

import java.util.Set;

public class DinnerRecipeMapper implements Mapper<DinnerRecipeResource, DinnerRecipe> {

    @Override
    public DinnerRecipe map(final DinnerRecipeResource request) {
        return new DinnerRecipe(null,
                request.getTitle(),
                request.getDishType(),
                request.getInstructions(),
                request.getServes(),
                (Set<Item>) new ItemMapper().map(request.getItems()));
    }

}