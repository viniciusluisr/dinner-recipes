package com.dinner.recipes.application.api.endpoint;

import com.dinner.recipes.application.api.mapper.entity.DinnerRecipeMapper;
import com.dinner.recipes.application.api.mapper.resource.DinnerRecipeResourceMapper;
import com.dinner.recipes.application.api.resource.DinnerRecipePageResponse;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.service.DinnerRecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Dinner recipes controller", description = "This API provides actions to create, read, update and list dinner recipes",
        basePath = "/api/v1/dinner-recipes", produces = "application/json")
@RequestMapping(value = "/api/v1/dinner-recipes")
@RestController
public class DinnerRecipeController {

    private final @NotNull
    DinnerRecipeService service;

    @ApiOperation(value = "Create a Dinner recipe with the given request object", response = DinnerRecipeResource.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> create(@RequestBody final DinnerRecipeResource request) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.create(new DinnerRecipeMapper().map(request))), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Dinner recipe with the given recipe id and request object", response = DinnerRecipeResource.class)
    @RequestMapping(value = "/{recipeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> update(@PathVariable(value = "recipeId") final String recipeId, @RequestBody final DinnerRecipeResource request) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.update(recipeId, new DinnerRecipeMapper().map(request))), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a Dinner recipe with the given recipe id", response = DinnerRecipeResource.class)
    @RequestMapping(value = "/{recipeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipeResource> find(@PathVariable(value = "recipeId") final String recipeId) {
        return new ResponseEntity<>(new DinnerRecipeResourceMapper().map(service.get(recipeId)), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a Dinner recipe with the given recipe id", response = DinnerRecipeResource.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<DinnerRecipePageResponse> list(@RequestParam(value = "page", defaultValue = "0") final Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") final Integer size) {
        return new ResponseEntity<>(new DinnerRecipePageResponse(new DinnerRecipeResourceMapper()
                .mapToRestResponsePage(service.list(new PageRequest(page, size)))), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Dinner recipe with the given recipe id", response = HttpStatus.class)
    @RequestMapping(value = "/{recipeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(value = "recipeId") final String recipeId) {
        service.delete(recipeId);
    }

    @ApiOperation(value = "Delete a Dinner recipe with the given recipe id", response = HttpStatus.class)
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAll() {
        service.deleteAll();
    }

}
