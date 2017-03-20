package com.dinner.recipes.application.service;

import com.dinner.recipes.domain.model.DinnerRecipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface DinnerRecipeService {

    DinnerRecipe create(final DinnerRecipe recipe);
    DinnerRecipe update(final String id, final DinnerRecipe recipe);
    void delete(final String id);
    DinnerRecipe get(final String id);
    Page<DinnerRecipe> list(final Pageable pageable);
    void deleteAll();
}