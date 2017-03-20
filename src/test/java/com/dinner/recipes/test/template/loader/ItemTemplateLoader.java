package com.dinner.recipes.test.template.loader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.DishType;
import com.dinner.recipes.domain.model.Ingredient;
import com.dinner.recipes.domain.model.Item;

import java.util.UUID;

public class ItemTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Item.class)
        .addTemplate("meatballs", new Rule() {
            {
                add("id", UUID.randomUUID().toString());
                add("name", "Meatballs");
                add("ingredients", has(7).of(Ingredient.class, "poundGroundPork",
                        "cupBreadcrumbs",
                        "finelyChoppedOnions",
                        "egg",
                        "garlicGloves",
                        "salt",
                        "pepper"));
            }
        }).addTemplate("currySauce", new Rule() {
            {
                add("id", UUID.randomUUID().toString());
                add("name", "Curry Sauce");
                add("ingredients", has(8).of(Ingredient.class, "tablespoonsDanishButter",
                        "tablespoonsYellowCurry",
                        "largeChoppedOnion",
                        "largeChoppedLeek",
                        "tablespoonsFlour",
                        "ouncesCream",
                        "choppedFreshParsley",
                        "cupsBeefStock"));
            }
        }).addTemplate("forTheChocolateCake", new Rule() {
            {
                add("id", UUID.randomUUID().toString());
                add("name", "Cake");
                add("ingredients", has(10).of(Ingredient.class, "plainFlour",
                        "casterSugar",
                        "cocoaPowder",
                        "bakingPowder",
                        "bicarbonateOfSoda",
                        "freeRangeEggs",
                        "milkForCake",
                        "vegetableOil",
                        "vanillaExtract",
                        "boilingWater"));
            }
        }).addTemplate("forTheChocolateCakeIcing", new Rule() {
            {
                add("id", UUID.randomUUID().toString());
                add("name", "Icing");
                add("ingredients", has(2).of(Ingredient.class, "plainChocolate", "doubleCream"));
            }
        });

    }
}