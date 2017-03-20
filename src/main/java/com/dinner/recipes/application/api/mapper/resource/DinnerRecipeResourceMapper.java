package com.dinner.recipes.application.api.mapper.resource;

import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.infra.mapper.Mapper;

import java.util.Set;

public class DinnerRecipeResourceMapper implements Mapper<DinnerRecipe, DinnerRecipeResource> {

    @Override
    public DinnerRecipeResource map(DinnerRecipe entity) {
        return new DinnerRecipeResource(entity.getId(),
                entity.getTitle(),
                entity.getDishType(),
                entity.getInstructions(),
                entity.getServes(),
                (Set<ItemResource>) new ItemResourceMapper().map(entity.getItems()));
    }
}