package com.dinner.recipes.application.service.provider;

import com.dinner.recipes.application.service.IngredientService;
import com.dinner.recipes.application.service.ItemService;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.Ingredient;
import com.dinner.recipes.domain.model.Item;
import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IngredientServiceProvider implements IngredientService {

    private final @NotNull
    ItemService itemService;

    @Override
    public DinnerRecipe addIngredient(final String recipeId, final String itemId, final Ingredient ingredient) {
        final Item item = itemService.get(itemId);

        Optional.of(item)
                .filter(i -> i.getIngredients().stream().noneMatch(j -> j.getName().equals(ingredient.getName())))
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The given ingredient already exists in " + item.getName() + " item: "))
                .addIngredient(ingredient);

        return itemService.update(recipeId, itemId, item);
    }

    @Override
    public DinnerRecipe removeIngredient(final String recipeId, final String itemId, final Ingredient ingredient) {
        final Item item = itemService.get(itemId);

        Optional.of(item)
                .filter(i -> i.getIngredients().stream().anyMatch(j -> j.getName().equals(ingredient.getName())))
                .orElseThrow(() -> new InvalidDinnerRecipeParameterException("The given ingredient does not belong to " + item.getName() + " item: "))
                .removeIngredient(ingredient);

        return itemService.update(recipeId, itemId, item);
    }

}
