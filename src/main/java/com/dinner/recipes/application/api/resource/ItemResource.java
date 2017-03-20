package com.dinner.recipes.application.api.resource;

import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException;
import com.dinner.recipes.infra.rest.AbstractResource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Optional;
import java.util.Set;

@ApiModel(value="Item Resource class", description="represents an object request or response for Dinner Recipe Item")
public class ItemResource extends AbstractResource {

    @ApiModelProperty(value = "represents an Dinner Recipe Item id")
    private final String itemId;
    @ApiModelProperty(value = "represents an Dinner Recipe Item name")
    private final String name;
    @ApiModelProperty(value = "represents an list of ingredients related of an Dinner Recipe Item")
    private final Set<IngredientResource> ingredients;

    @JsonCreator
    public ItemResource(@JsonProperty("itemId") String itemId,
                        @JsonProperty("name") String name,
                        @JsonProperty("ingredients") Set<IngredientResource> ingredients) {
        this.itemId = itemId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return Optional.ofNullable(name)
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The Dinner recipe item name can't be null."));
    }

    public Set<IngredientResource> getIngredients() {
        return Optional.ofNullable(ingredients)
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The Dinner recipe should has at least one ingredient."));
    }
}
