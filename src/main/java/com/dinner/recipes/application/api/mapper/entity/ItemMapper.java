package com.dinner.recipes.application.api.mapper.entity;

import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.domain.model.Ingredient;
import com.dinner.recipes.domain.model.Item;
import com.dinner.recipes.infra.mapper.Mapper;

import java.util.Set;

public class ItemMapper implements Mapper<ItemResource, Item> {

    @Override
    public Item map(final ItemResource resource) {
        return new Item(null,
                resource.getName(),
                (Set<Ingredient>) new IngredientMapper().map(resource.getIngredients()));
    }

}