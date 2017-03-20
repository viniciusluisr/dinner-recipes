package com.dinner.recipes.application.api.mapper.entity;

import com.dinner.recipes.application.api.resource.IngredientResource;
import com.dinner.recipes.domain.model.Ingredient;
import com.dinner.recipes.infra.mapper.Mapper;

public class IngredientMapper implements Mapper<IngredientResource, Ingredient> {

    @Override
    public Ingredient map(final IngredientResource resource) {
        return new Ingredient(resource.getName(),
                resource.getQuantity(),
                resource.getUnit());
    }

}