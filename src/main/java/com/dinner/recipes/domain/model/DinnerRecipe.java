package com.dinner.recipes.domain.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Value
@Document
public class DinnerRecipe {

    @Id
    private String id;
    private String title;
    private DishType dishType;
    private String instructions;
    private Integer serves;
    private Set<Item> items;

    public void addItem(final Item item) {
        items.add(item);
    }
    public void removeItem(final Item item) {
        items.remove(item);
    }

}