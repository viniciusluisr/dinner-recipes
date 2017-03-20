package com.dinner.recipes.domain.model;

import lombok.Data;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Ingredient {

    private String name;
    private Integer quantity;
    private String unit;

    public Ingredient() {
    }

    public Ingredient(String name, Integer quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}
