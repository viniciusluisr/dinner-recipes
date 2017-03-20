package com.dinner.recipes.domain.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Value
@Document
public class Item {

    @Id
    private String id;
    private String name;
    private Set<Ingredient> ingredients;

    public void addIngredient(final Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(final Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

}
