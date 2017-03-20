package com.dinner.recipes.test.template.loader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.DishType;
import com.dinner.recipes.domain.model.Item;

import java.util.UUID;

public class DinnerRecipeTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(DinnerRecipe.class)
        .addTemplate("bollerIKarry", new Rule() {
            {
                add("id", UUID.randomUUID().toString());
                add("title", "Boller i karr");
                add("dishType", DishType.MAIN);
                add("instructions", "Meatballs:\n" +
                        "\n" +
                        "Mix Breadcrumbs or flour, egg, onions, garlic salt and pepper go into a big bowl.\n" +
                        "*Be sure to mix it well*\n" +
                        "Add ground pork and mix it well again\n" +
                        "Leave the mixture in the fridge for 1 hour\n" +
                        "Make meatballs with a tablespoon, and place in boiling water with the beef stock.\n" +
                        "Boil for 5-10 minutes, depending on the size of the meatballs\n" +
                        "Take meatballs out of the water, but keep the water (you will need it for the Curry Sauce)\n" +
                        "Curry Sauce:\n" +
                        "\n" +
                        "Melt butter in a pot\n" +
                        "Add curry and brown it for a couple of minutes\n" +
                        "Add onions and leek and brown it for a couple of minutes\n" +
                        "Add flour and mix well\n" +
                        "Add water from meatballs a little bit at the time, until the sauce thickens.\n" +
                        "Add cream and meatballs\n" +
                        "Let it simmer for 12 min.");
                add("serves", random(Integer.class, range(1, 10)));
                add("items", has(2).of(Item.class, "meatballs", "currySauce"));
            }
        }).addTemplate("chocolateCake", new Rule() {
            {
                add("id", UUID.randomUUID().toString());
                add("title", "Chocolate cake");
                add("dishType", DishType.DESERT);
                add("instructions", "Preheat the oven to 180C/350F/Gas 4. Grease and line two 20cm/8in sandwich tins.\n" +
                        "For the cake, place all of the cake ingredients, except the boiling water, into a large mixing bowl. Using a wooden spoon, or electric whisk, beat the mixture until smooth and well combined.\n" +
                        "Add the boiling water to the mixture, a little at a time, until smooth. (The cake mixture will now be very liquid.)\n" +
                        "Divide the cake batter between the sandwich tins and bake in the oven for 25-35 minutes, or until the top is firm to the touch and a skewer inserted into the centre of the cake comes out clean.\n" +
                        "Remove the cakes from the oven and allow to cool completely, still in their tins, before icing.\n" +
                        "For the chocolate icing, heat the chocolate and cream in a saucepan over a low heat until the chocolate melts. Remove the pan from the heat and whisk the mixture until smooth, glossy and thickened. Set aside to cool for 1-2 hours, or until thick enough to spread over the cake.\n" +
                        "To assemble the cake, run a round-bladed knife around the inside of the cake tins to loosen the cakes. Carefully remove the cakes from the tins.\n" +
                        "Spread a little chocolate icing over the top of one of the chocolate cakes, then carefully top with the other cake.\n" +
                        "Transfer the cake to a serving plate and ice the cake all over with the chocolate icing, using a palette knife.");
                add("serves", random(Integer.class, range(1, 10)));
                add("items", has(2).of(Item.class, "forTheChocolateCake", "forTheChocolateCakeIcing"));
            }
        });

    }
}