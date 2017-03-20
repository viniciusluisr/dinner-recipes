package com.dinner.recipes.application.api.endpoint;

import com.dinner.recipes.application.api.mapper.entity.ItemMapper;
import com.dinner.recipes.application.api.mapper.resource.DinnerRecipeResourceMapper;
import com.dinner.recipes.application.api.mapper.resource.ItemResourceMapper;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.application.service.ItemService;
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
@Api(value = "Dinner recipe items controller", description = "This API provides to create, read, update and get dinner recipe items",
        basePath = "/api/v1/dinner-recipes/{recipeId}/items", produces = "application/json")
@RequestMapping(value = "/api/v1/dinner-recipes/{recipeId}/items")
@RestController
public class ItemController {

    private final @NotNull
    ItemService service;

    @ApiOperation(value = "Add an item to an existing dinner recipe with the given recipe id and request object", response = DinnerRecipeResource.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> addItem(@PathVariable(value = "recipeId") final String recipeId, @RequestBody final ItemResource request) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.addItem(recipeId, new ItemMapper().map(request))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an existing dinner recipe item with the given recipe id, item id and request object", response = DinnerRecipeResource.class)
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> update(@PathVariable(value = "recipeId") final String recipeId, @PathVariable(value = "itemId") final String itemId, @RequestBody final ItemResource request) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.update(recipeId, itemId, new ItemMapper().map(request))), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a Dinner recipe with the given recipe id", response = DinnerRecipeResource.class)
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ItemResource> find(@PathVariable(value = "recipeId") final String recipeId, @PathVariable(value = "itemId") final String itemId) {
        return new ResponseEntity<>(new ItemResourceMapper().map(service.get(itemId)), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove an item of an existing dinner recipe with the given recipe id and request object", response = DinnerRecipeResource.class)
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> removeItem(@PathVariable(value = "recipeId") final String recipeId, @PathVariable(value = "itemId") final String itemId) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.removeItem(recipeId, itemId)), HttpStatus.OK);
    }
}