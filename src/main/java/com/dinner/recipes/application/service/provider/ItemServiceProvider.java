package com.dinner.recipes.application.service.provider;

import com.dinner.recipes.application.service.DinnerRecipeService;
import com.dinner.recipes.application.service.ItemService;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.Item;
import com.dinner.recipes.domain.repository.ItemRepository;
import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException;
import com.dinner.recipes.infra.exception.InvalidRecipeTitleException;
import com.dinner.recipes.infra.exception.RecipeItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ItemServiceProvider implements ItemService {

    private final @NotNull DinnerRecipeService dinnerRecipeService;
    private final @NotNull ItemRepository repository;


    @Override
    public DinnerRecipe addItem(final String recipeId, final Item item) {
        final DinnerRecipe recipe = dinnerRecipeService.get(recipeId);

        Optional.of(recipe)
                .filter(i -> i.getItems().stream().noneMatch(j -> j.getName().equals(item.getName())))
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The given item already exists in " + recipe.getTitle() + " recipe"))
                .addItem(item);

        return dinnerRecipeService.update(recipeId, recipe);
    }

    @Override
    public DinnerRecipe removeItem(final String recipeId, final String id) {
        final DinnerRecipe recipe = dinnerRecipeService.get(recipeId);
        final Item item = get(id);

        Optional.of(recipe)
                .filter(i -> i.getItems().stream().anyMatch(j -> j.getName().equals(item.getName())))
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The given item does not belong to " + recipe.getTitle() + " recipe"))
                .removeItem(item);

        repository.delete(id);
        return dinnerRecipeService.update(recipeId, recipe);
    }

    @Override
    public DinnerRecipe update(final String recipeId, final String id, final Item item) {
        final DinnerRecipe recipe = dinnerRecipeService.get(recipeId);
        Optional.ofNullable(get(id))
            .ifPresent(i -> {
                 if(recipe.getItems().stream().anyMatch(j -> j.getId().equals(id))) {
                     Item toUpdate = new Item(id, item.getName(), item.getIngredients());

                     final Set<Item> withoutGivenItem = recipe.getItems();
                     recipe.removeItem(i);

                     withoutGivenItem.stream()
                             .filter(j -> j.getName().equals(toUpdate.getName()))
                             .findFirst()
                             .ifPresent(f -> {
                                 throw new InvalidDinnerRecipeParameterException("The given item name already exists in another item of " + recipe.getTitle() + "recipe");
                             });

                     recipe.getItems().stream()
                             .filter(j -> j.getName().equals(toUpdate.getName()))
                             .findFirst()
                             .ifPresent(recipe::removeItem);

                     recipe.addItem(toUpdate);
                     dinnerRecipeService.update(recipeId, recipe);
                 } else {
                    throw new RecipeItemNotFoundException("The given item does not belong to " + recipe.getTitle() + " recipe");
                 }

            });

        return recipe;
    }

    @Override
    public Item get(final String id) {
        return Optional.ofNullable(repository.findOne(id))
                .orElseThrow(() -> new RecipeItemNotFoundException("Recipe item not found with given id: " + id));
    }


}