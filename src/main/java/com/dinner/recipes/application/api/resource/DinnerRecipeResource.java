package com.dinner.recipes.application.api.resource;

import com.dinner.recipes.domain.model.DishType;
import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException;
import com.dinner.recipes.infra.rest.AbstractResource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Optional;
import java.util.Set;

@ApiModel(value="Dinner Recipe Resource class", description="represents an object request or response for Dinner Recipe")
public class DinnerRecipeResource extends AbstractResource {

    @ApiModelProperty(value = "represents an Dinner Recipe id")
    private final String dinnerRecipeId;
    @ApiModelProperty(value = "represents an Dinner Recipe title")
    private final String title;
    @ApiModelProperty(value = "represents an Dinner Recipe dish type")
    private final DishType dishType;
    @ApiModelProperty(value = "represents the instructions of a Dinner Recipe")
    private final String instructions;
    @ApiModelProperty(value = "represents how many guests does the Dinner Recipe serves")
    private final Integer serves;
    @ApiModelProperty(value = "represents an list of items related of an Dinner Recipe")
    private final Set<ItemResource> items;

    @JsonCreator
    public DinnerRecipeResource(@JsonProperty("dinnerRecipeId") String dinnerRecipeId,
                               @JsonProperty("title") String title,
                               @JsonProperty("dishType") DishType dishType,
                               @JsonProperty("instructions") String instructions,
                               @JsonProperty("serves") Integer serves,
                               @JsonProperty("items") Set<ItemResource> items) {
        this.dinnerRecipeId = dinnerRecipeId;
        this.title = title;
        this.dishType = dishType;
        this.instructions = instructions;
        this.serves = serves;
        this.items = items;
    }

    public String getDinnerRecipeId() {
        return dinnerRecipeId;
    }

    public String getTitle() {
        return Optional.ofNullable(title)
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The Dinner recipe title can't be null."));
    }

    public DishType getDishType() {
        return Optional.ofNullable(dishType)
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The Dinner recipe dish type can't be null."));
    }

    public String getInstructions() {
        return Optional.ofNullable(instructions)
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The Dinner recipe dish instructions can't be null."));
    }

    public Integer getServes() {
        return serves;
    }

    public Set<ItemResource> getItems() {
        return Optional.ofNullable(items)
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The Dinner recipe should has at least one item."));
    }

}
