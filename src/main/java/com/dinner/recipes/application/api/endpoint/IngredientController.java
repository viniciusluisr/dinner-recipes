package com.dinner.recipes.application.api.endpoint;

import com.dinner.recipes.application.api.mapper.entity.IngredientMapper;
import com.dinner.recipes.application.api.mapper.resource.DinnerRecipeResourceMapper;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.IngredientResource;
import com.dinner.recipes.application.service.IngredientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Dinner recipes ingredients controller", description = "This API provides actions to add or remove ingredients in a given recipe",
        basePath = "/api/v1/dinner-recipes/{recipeId}/items/{itemId}/ingredients", produces = "application/json")
@RequestMapping(value = "/api/v1/dinner-recipes/{recipeId}/items/{itemId}/ingredients")
@RestController
public class IngredientController {

    private final @NotNull
    IngredientService service;

    @ApiOperation(value = "Add an ingredient to an existing dinner recipe with the given recipe id and request object", response = DinnerRecipeResource.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> addIngredient(@PathVariable(value = "recipeId") final String recipeId,
                                                          @PathVariable(value = "itemId") final String itemId,
                                                          @RequestBody final IngredientResource request) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.addIngredient(recipeId, itemId, new IngredientMapper().map(request))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove an ingredient of an existing dinner recipe with the given recipe id and request object", response = DinnerRecipeResource.class)
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> removeIngredient(@PathVariable(value = "recipeId") final String recipeId,
                                                             @PathVariable(value = "itemId") final String itemId,
                                                             @RequestBody final IngredientResource request) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.removeIngredient(recipeId, itemId, new IngredientMapper().map(request))), HttpStatus.OK);
    }

}