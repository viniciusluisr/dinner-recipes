package com.dinner.recipes.test.template.loader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.dinner.recipes.domain.model.Ingredient;

public class IngredientTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Ingredient.class)
            .addTemplate("poundGroundPork", new Rule() {
            {
                add("name", "pound ground pork");
                add("quantity", 1);
            }
        }).addTemplate("cupBreadcrumbs", new Rule() {
            {
                add("name", "cup breadcrumbs");
                add("quantity", 1);
            }
        }).addTemplate("finelyChoppedOnions", new Rule() {
            {
                add("name", "finely chopped onions");
                add("quantity", 2);
            }
        }).addTemplate("egg", new Rule() {
            {
                add("name", "egg");
                add("quantity", 1);
            }
        }).addTemplate("garlicGloves", new Rule() {
            {
                add("name", "garlic gloves");
                add("quantity", 3);
            }
        }).addTemplate("salt", new Rule() {
            {
                add("name", "salt");
                add("unit", "3g");
            }
        }).addTemplate("pepper", new Rule() {
            {
                add("name", "pepper");
                add("unit", "freely");
            }
        }).addTemplate("tablespoonsDanishButter", new Rule() {
            {
                add("name", "Tablespoons Danish butter");
                add("quantity", 2);
            }
        }).addTemplate("tablespoonsYellowCurry", new Rule() {
            {
                add("name", "Tablespoons yellow curry");
                add("quantity", 2);
            }
        }).addTemplate("largeChoppedOnion", new Rule() {
            {
                add("name", "Large chopped onion");
                add("quantity", 1);
            }
        }).addTemplate("largeChoppedLeek", new Rule() {
            {
                add("name", "Large chopped leek");
                add("quantity", 1);
            }
        }).addTemplate("tablespoonsFlour", new Rule() {
            {
                add("name", "Tablespoons flour");
                add("quantity", 5);
            }
        }).addTemplate("ouncesCream", new Rule() {
            {
                add("name", "Ounces cream 35%");
                add("quantity", 3);
            }
        }).addTemplate("choppedFreshParsley", new Rule() {
            {
                add("name", "Chopped fresh parsley");
                add("quantity", 1);
            }
        }).addTemplate("cupsBeefStock", new Rule() {
            {
                add("name", "Cups Beef stock");
                add("quantity", 4);
            }
        }).addTemplate("plainFlour", new Rule() {
            {
                add("name", "Plain flour");
                add("unit", "225g");
            }
        }).addTemplate("casterSugar", new Rule() {
            {
                add("name", "Caster sugar");
                add("unit", "350g");
            }
        }).addTemplate("cocoaPowder", new Rule() {
            {
                add("name", "Cocoa powder");
                add("unit", "85g");
            }
        }).addTemplate("bakingPowder", new Rule() {
            {
                add("name", "Baking powder");
                add("unit", "1½ tsp");
            }
        }).addTemplate("bicarbonateOfSoda", new Rule() {
            {
                add("name", "Bicarbonate of soda");
                add("unit", "1½ tsp");
            }
        }).addTemplate("freeRangeEggs", new Rule() {
            {
                add("name", "Free-range eggs");
                add("quantity", 2);
            }
        }).addTemplate("milkForCake", new Rule() {
            {
                add("name", "Milk");
                add("unit", "250ml");
            }
        }).addTemplate("vegetableOil", new Rule() {
            {
                add("name", "Vegetable oil");
                add("unit", "125ml");
            }
        }).addTemplate("vanillaExtract", new Rule() {
            {
                add("name", "Vanilla extract");
                add("unit", "2 tsp");
            }
        }).addTemplate("boilingWater", new Rule() {
            {
                add("name", "Boiling water");
                add("unit", "250ml");
            }
        }).addTemplate("plainChocolate", new Rule() {
            {
                add("name", "Plain chocolate");
                add("unit", "200g");
            }
        }).addTemplate("doubleCream", new Rule() {
            {
                add("name", "Double cream");
                add("unit", "200ml");
            }
        });
    }
}