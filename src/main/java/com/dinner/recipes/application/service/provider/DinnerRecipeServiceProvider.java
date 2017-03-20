package com.dinner.recipes.application.service.provider;

import com.dinner.recipes.application.service.DinnerRecipeService;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.repository.DinnerRecipeRepository;
import com.dinner.recipes.domain.repository.ItemRepository;
import com.dinner.recipes.infra.exception.InvalidRecipeTitleException;
import com.dinner.recipes.infra.exception.RecipeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DinnerRecipeServiceProvider implements DinnerRecipeService {

    private final @NotNull
    DinnerRecipeRepository dinnerRecipeRepository;
    private final @NotNull ItemRepository itemRepository;

    @Override
    public DinnerRecipe create(final DinnerRecipe recipe) {
        validateRecipeTitleToSave(recipe.getTitle());
        itemRepository.save(recipe.getItems());
        return dinnerRecipeRepository.save(recipe);
    }

    @Override
    public DinnerRecipe update(final String id, final DinnerRecipe recipe) {
        Optional.ofNullable(get(id))
                .ifPresent(r -> {
                    validateRecipeTitleToUpdate(id, recipe.getTitle());
                    itemRepository.save(recipe.getItems());
                    updateDinnerRecipe(r, recipe);
                });

        return get(id);
    }

    @Override
    public void delete(final String id) {
        Optional.ofNullable(get(id))
            .ifPresent(d -> dinnerRecipeRepository.delete(d.getId()));
    }

    @Override
    public DinnerRecipe get(final String id) {
        return Optional.ofNullable(dinnerRecipeRepository.findOne(id))
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with given id: " + id));
    }

    @Override
    public Page<DinnerRecipe> list(final Pageable pageable) {
        return dinnerRecipeRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        dinnerRecipeRepository.deleteAll();
        itemRepository.deleteAll();
    }

    private void updateDinnerRecipe(final DinnerRecipe previews, final DinnerRecipe toSave) {
        DinnerRecipe toUpdate = new DinnerRecipe(previews.getId(),
                toSave.getTitle(),
                toSave.getDishType(),
                toSave.getInstructions(),
                toSave.getServes(),
                toSave.getItems());
        dinnerRecipeRepository.save(toUpdate);
    }

    private void validateRecipeTitleToSave(final String title) {
        Optional.ofNullable(dinnerRecipeRepository.findByTitle(title))
            .ifPresent(r -> {
            throw new InvalidRecipeTitleException("The given Dinner recipe title already exists.");
        });
    }

    private void validateRecipeTitleToUpdate(final String id, final String title) {
        Optional.ofNullable(dinnerRecipeRepository.findByTitle(title))
            .ifPresent(r -> {
                if(!r.getId().equals(id)) {
                    throw new InvalidRecipeTitleException("The given Dinner recipe title already exists.");
                }
            });
    }

}
