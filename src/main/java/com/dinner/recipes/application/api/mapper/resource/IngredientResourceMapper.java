package com.dinner.recipes.application.api.mapper.resource;

import com.dinner.recipes.application.api.resource.IngredientResource;
import com.dinner.recipes.domain.model.Ingredient;
import com.dinner.recipes.infra.mapper.Mapper;

public class IngredientResourceMapper implements Mapper<Ingredient, IngredientResource> {

    @Override
    public IngredientResource map(final Ingredient entity) {
        return new IngredientResource(entity.getName(),
                entity.getQuantity(),
                entity.getUnit());
    }

}