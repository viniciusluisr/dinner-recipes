package com.dinner.recipes.application.api.resource;

import com.dinner.recipes.infra.rest.RestResponsePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import java.io.Serializable;

@ApiModel(value="Dinner Recipe Page class", description="represents an object response of a paginated Dinner Recipe")
@Value
public class DinnerRecipePageResponse implements Serializable {

    @ApiModelProperty(value = "represents an Dinner Recipe paginated response")
    private RestResponsePage<DinnerRecipeResource> response;

}
