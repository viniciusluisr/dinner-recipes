package com.dinner.recipes.test.application.endpoint;

import br.com.six2six.fixturefactory.Fixture;
import com.dinner.recipes.application.api.mapper.resource.DinnerRecipeResourceMapper;
import com.dinner.recipes.application.api.resource.DinnerRecipePageResponse;
import com.dinner.recipes.application.api.resource.DinnerRecipeResource;
import com.dinner.recipes.domain.model.DinnerRecipe;
import com.dinner.recipes.domain.model.DishType;
import com.dinner.recipes.test.support.TestDinnerRecipeApiEndpoints;
import com.dinner.recipes.test.support.TestFixtureSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DinnerRecipeControllerTest extends TestFixtureSupport {

    @Override
    public void setUp() {

    }

    @Test
    public void testCreate() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource request = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> response = TestDinnerRecipeApiEndpoints.create(request);
        final DinnerRecipeResource body = response.getBody();

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(request.getTitle(), body.getTitle());
        assertEquals(request.getDishType(), body.getDishType());
        assertEquals(request.getInstructions(), body.getInstructions());
        assertEquals(request.getServes(), body.getServes());
        assertEquals(request.getItems().size(), body.getItems().size());
        assertNotNull(body.getInstructions());

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testCreateAnAlreadyExistingRecipe() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource request = new DinnerRecipeResourceMapper().map(recipe);

        TestDinnerRecipeApiEndpoints.create(request);
        final ResponseEntity<DinnerRecipeResource> response = TestDinnerRecipeApiEndpoints.create(request);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testUpdate() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource request1 = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> response1 = TestDinnerRecipeApiEndpoints.create(request1);
        final DinnerRecipeResource body1 = response1.getBody();

        final DinnerRecipeResource request2 = new DinnerRecipeResource(body1.getId(),
                "Egg's salad",
                DishType.STARTER,
                body1.getInstructions(),
                5,
                body1.getItems());

        TestDinnerRecipeApiEndpoints.update(request1.getId(), request2);
        final ResponseEntity<DinnerRecipeResource> response2 = TestDinnerRecipeApiEndpoints.find(body1.getId());
        DinnerRecipeResource body2 = response2.getBody();

        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        assertEquals(body1.getId(), body2.getId());
        assertEquals(body1.getTitle(), body2.getTitle());
        assertEquals(body1.getDishType(), body2.getDishType());
        assertEquals(body1.getInstructions(), body2.getInstructions());
        assertEquals(body1.getServes(), body2.getServes());
        assertEquals(body1.getItems(), body2.getItems());

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testFind() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource request = new DinnerRecipeResourceMapper().map(recipe);

        final ResponseEntity<DinnerRecipeResource> response = TestDinnerRecipeApiEndpoints.create(request);
        final DinnerRecipeResource body = response.getBody();

        final ResponseEntity<DinnerRecipeResource> response2 = TestDinnerRecipeApiEndpoints.find(body.getId());
        final DinnerRecipeResource body2 = response2.getBody();

        assertEquals(response2.getStatusCode(), HttpStatus.OK);
        assertNotNull(body2.getId());
        assertEquals(request.getTitle(), body2.getTitle());
        assertEquals(request.getDishType(), body2.getDishType());
        assertEquals(request.getInstructions(), body2.getInstructions());
        assertEquals(request.getServes(), body2.getServes());
        assertEquals(request.getItems().size(), body2.getItems().size());

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testFindNonExistingRecipe() {
        final ResponseEntity<DinnerRecipeResource> response = TestDinnerRecipeApiEndpoints.find("id");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testList() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe1 = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipe recipe2 = Fixture.from(DinnerRecipe.class).gimme("chocolateCake");
        final DinnerRecipeResource request1 = new DinnerRecipeResourceMapper().map(recipe1);
        final DinnerRecipeResource request2 = new DinnerRecipeResourceMapper().map(recipe2);

        TestDinnerRecipeApiEndpoints.create(request1);
        TestDinnerRecipeApiEndpoints.create(request2);

        final ResponseEntity<DinnerRecipePageResponse> response = TestDinnerRecipeApiEndpoints.list();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getResponse().getContent().size(), 2);

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

    @Test
    public void testDelete() {
        TestDinnerRecipeApiEndpoints.deleteAll();

        final DinnerRecipe recipe = Fixture.from(DinnerRecipe.class).gimme("bollerIKarry");
        final DinnerRecipeResource request = new DinnerRecipeResourceMapper().map(recipe);
        final ResponseEntity<DinnerRecipeResource> response1 = TestDinnerRecipeApiEndpoints.create(request);

        TestDinnerRecipeApiEndpoints.delete(response1.getBody().getId());
        ResponseEntity<DinnerRecipeResource> response2 = TestDinnerRecipeApiEndpoints.find(response1.getBody().getId());
        assertEquals(response2.getStatusCode(), HttpStatus.NOT_FOUND);

        TestDinnerRecipeApiEndpoints.deleteAll();
    }

}