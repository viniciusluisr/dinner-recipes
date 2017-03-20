package com.dinner.recipes.application.api.mapper.resource;

import com.dinner.recipes.application.api.endpoint.DinnerRecipeController;
import com.dinner.recipes.application.api.endpoint.ItemController;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.infra.mapper.Mapper;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DinnerRecipeResourceMapper implements Mapper<DinnerRecipe, DinnerRecipeResource> {

    @Override
    public DinnerRecipeResource map(DinnerRecipe entity) {
       final DinnerRecipeResource resource = new DinnerRecipeResource(entity.getId(),
                entity.getTitle(),
                entity.getDishType(),
                entity.getInstructions(),
                entity.getServes(),
                (Set<ItemResource>) new ItemResourceMapper().map(entity.getItems()));

        resource.getItems().forEach(i -> i.add(linkTo(methodOn(ItemController.class).find(entity.getId(), i.getItemId())).withSelfRel()));
        resource.add(linkTo(methodOn(DinnerRecipeController.class).find(entity.getId())).withSelfRel());

        return resource;

    }
}