package com.dinner.recipes.application.api.resource;

import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException;
import com.dinner.recipes.infra.rest.AbstractResource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Optional;

@ApiModel(value="Ingredient Resource class", description="represents an object request or response for Dinner Recipe Item Ingredient")
public class IngredientResource extends AbstractResource {

    @ApiModelProperty(value = "represents an Ingredient name related of a Dinner Recipe Item")
    private final String name;
    @ApiModelProperty(value = "represents an Ingredient quantity related of a Dinner Recipe Item")
    private final Integer quantity;
    @ApiModelProperty(value = "represents an Ingredient unit related of a Dinner Recipe Item")
    private final String unit;

    @JsonCreator
    public IngredientResource(@JsonProperty("name") String name,
                              @JsonProperty("quantity") Integer quantity,
                              @JsonProperty("unit") String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return Optional.ofNullable(name)
                    .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The ingredient title can't be null."));
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}
