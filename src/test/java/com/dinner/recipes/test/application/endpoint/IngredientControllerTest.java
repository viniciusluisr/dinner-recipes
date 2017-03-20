package com.dinner.recipes.test.application.endpoint;

import br.com.six2six.fixturefactory.Fixture;
import com.dinner.recipes.application.api.mapper.resource.DinnerRecipeResourceMapper;
import com.dinner.recipes.application.api.mapper.resource.IngredientResourceMapper;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.IngredientResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.Ingredient;
import com.dinner.recipes.test.support.TestDinnerRecipeApiEndpoints;
import com.dinner.recipes.test.support.TestFixtureSupport;
import com.dinner.recipes.test.support.TestIngredientApiEndpoints;
import com.dinner.recipes.test.support.TestItemApiEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IngredientControllerTest extends TestFixtureSupport {

    @Override
    public void setUp() {

    }

    @Test
    public void testAddItem() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        final Ingredient ingredient = Fixture.from(Ingredient.class).gimme("plainChocolate");
        final IngredientResource ingredientRequest = new IngredientResourceMapper().map(ingredient);

        final ResponseEntity<DinnerRecipeResource> response = TestIngredientApiEndpoints.addIngredient(dinnerRecipeBody.getDinnerRecipeId(), itemRequest.getItemId(), ingredientRequest);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        final ItemResource body = TestItemApiEndpoints.find(dinnerRecipeBody.getDinnerRecipeId(), itemRequest.getItemId()).getBody();

        assertEquals(body.getIngredients().size(), itemRequest.getIngredients().size() + 1);
        assertTrue(body.getIngredients().contains(ingredientRequest));

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testAddAnAlreadyExistingIngredient() {

        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        final Ingredient ingredient = Fixture.from(Ingredient.class).gimme("poundGroundPork");
        final IngredientResource ingredientRequest = new IngredientResourceMapper().map(ingredient);

        final ResponseEntity<DinnerRecipeResource> response = TestIngredientApiEndpoints.addIngredient(dinnerRecipeBody.getDinnerRecipeId(), itemRequest.getItemId(), ingredientRequest);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        TestDinnerRecipeApiEndpoints.deleteAll();

    }

    @Test
    public void testRemoveItem() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        final IngredientResource ingredientRequest = itemRequest.getIngredients().stream().findFirst().get();

        TestIngredientApiEndpoints.removeIngredient(dinnerRecipeBody.getDinnerRecipeId(), itemRequest.getItemId(), ingredientRequest);

        final ItemResource body = TestItemApiEndpoints.find(dinnerRecipeBody.getDinnerRecipeId(), itemRequest.getItemId()).getBody();

        assertEquals(body.getIngredients().size(), itemRequest.getIngredients().size() - 1);
        assertFalse(body.getIngredients().contains(ingredientRequest));

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

}