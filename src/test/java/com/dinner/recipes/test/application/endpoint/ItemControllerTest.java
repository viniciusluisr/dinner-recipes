package com.dinner.recipes.test.application.endpoint;

import br.com.six2six.fixturefactory.Fixture;
import com.dinner.recipes.application.api.mapper.resource.DinnerRecipeResourceMapper;
import com.dinner.recipes.application.api.mapper.resource.ItemResourceMapper;
import com.dinner.recipes.application.api.resource.DinnerRecipePageResponse;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.application.api.resource.ItemResource;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.DishType;
import com.dinner.recipes.domain.model.Item;
import com.dinner.recipes.test.support.TestDinnerRecipeApiEndpoints;
import com.dinner.recipes.test.support.TestFixtureSupport;
import com.dinner.recipes.test.support.TestItemApiEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ItemControllerTest extends TestFixtureSupport {

    @Override
    public void setUp() {

    }

    @Test
    public void testAddItem() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final Item item = Fixture.from(Item.class).gimme("meatballs");
        final ItemResource itemRequest = new ItemResourceMapper().map(item);

        final ResponseEntity<DinnerRecipeResource> itemResponse = TestItemApiEndpoints.addItem(dinnerRecipeBody.getId(), itemRequest);
        final DinnerRecipeResource itemBody = itemResponse.getBody();

        assertEquals(itemResponse.getStatusCode(), HttpStatus.CREATED);
        assertEquals(itemResponse.getBody().getItems().size(), 3);
        assertNotNull(itemBody.getId());

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testAddAnAlreadyExistingItem() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        final ResponseEntity<DinnerRecipeResource> itemResponse = TestItemApiEndpoints.addItem(dinnerRecipeBody.getId(), itemRequest);

        assertEquals(itemResponse.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testUpdate() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        final ItemResource updatedRequestItem = new ItemResource(itemRequest.getId(),
                "New item",
                itemRequest.getIngredients());

        TestItemApiEndpoints.update(dinnerRecipeBody.getId(), itemRequest.getId(), updatedRequestItem);

        final ResponseEntity<ItemResource> itemResponse = TestItemApiEndpoints.find(dinnerRecipeBody.getId(), updatedRequestItem.getId());
        final ItemResource itemBody = itemResponse.getBody();


        assertEquals(itemResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(itemBody.getName(), updatedRequestItem.getName());
        assertNotNull(itemBody.getId());

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testFind() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        final ResponseEntity<ItemResource> itemResponse = TestItemApiEndpoints.find(dinnerRecipeBody.getId(), itemRequest.getId());
        final ItemResource itemBody = itemResponse.getBody();

        assertEquals(itemResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(itemBody.getId());

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testFindNonExistingItem() {
        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ResponseEntity<ItemResource> itemResponse = TestItemApiEndpoints.find(dinnerRecipeBody.getId(), "id");

        assertEquals(itemResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testRemoveItem() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource dinnerRecipeRequest = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> dinnerRecipeResponse = TestDinnerRecipeApiEndpoints.create(dinnerRecipeRequest);
        final DinnerRecipeResource dinnerRecipeBody = dinnerRecipeResponse.getBody();

        final ItemResource itemRequest = dinnerRecipeBody.getItems().stream().findFirst().get();

        TestItemApiEndpoints.removeItem(dinnerRecipeBody.getId(), itemRequest.getId());

        final ResponseEntity<ItemResource> itemResponse = TestItemApiEndpoints.find(dinnerRecipeBody.getId(), itemRequest.getId());

        assertEquals(itemResponse.getStatusCode(), HttpStatus.NOT_FOUND);

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

}