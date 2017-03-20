package com.dinner.recipes.application.api.mapper.resource;

import com.dinner.recipes.application.api.resource.IngredientResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.domain.model.Item;
import com.dinner.recipes.infra.mapper.Mapper;

import java.util.Set;

public class ItemResourceMapper implements Mapper<Item, ItemResource> {

    @Override
    public ItemResource map(final Item entity) {
        return new ItemResource(entity.getId(),
                entity.getName(),
                (Set<IngredientResource>) new IngredientResourceMapper().map(entity.getIngredients()));
    }

}