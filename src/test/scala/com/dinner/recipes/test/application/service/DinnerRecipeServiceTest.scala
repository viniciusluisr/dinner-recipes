package com.dinner.recipes.test.application.service

import br.com.six2six.fixturefactory.Fixture
import com.dinner.recipes.application.service.DinnerRecipeService
import com.dinner.recipes.application.service.provider.DinnerRecipeServiceProvider
import com.dinner.recipes.domain.model.DinnerRecipe
import com.dinner.recipes.domain.repository.{DinnerRecipeRepository, ItemRepository}
import com.dinner.recipes.infra.exception.{InvalidRecipeTitleException, RecipeNotFoundException}
import com.dinner.recipes.test.support.ScalaTestSupport
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DinnerRecipeServiceTest extends ScalaTestSupport {

  private val dinnerRecipeRepository:DinnerRecipeRepository = mock[DinnerRecipeRepository]
  private val itemRepository:ItemRepository = mock[ItemRepository]
  private val service: DinnerRecipeService = new DinnerRecipeServiceProvider(dinnerRecipeRepository, itemRepository)

  feature("Create new Dinner Recipes") {

    scenario("Creating a valid Dinner Recipe") {

      Given("a valid Dinner Recipe")
      val recipe: DinnerRecipe = bollerIKarryRecipe()

      And("the Dinner Recipe is valid")
      Mockito.when(dinnerRecipeRepository.findByTitle(recipe.getTitle))
        .thenReturn(null)

      Then("should save the Dinner Recipe")
      val expected: DinnerRecipe = service.create(recipe)

      val order = Mockito.inOrder(dinnerRecipeRepository, itemRepository)
      order verify dinnerRecipeRepository findByTitle recipe.getTitle
      order verify itemRepository save recipe.getItems
      order verify dinnerRecipeRepository save recipe

    }

    scenario("Trying to create a Dinner Recipe with an already existing title") {

      Given("a valid Dinner Recipe")
      val recipe: DinnerRecipe = bollerIKarryRecipe()

      And("with a valid recipe title")
      validRecipeWithGivenTitle(recipe)

      Then("shouldn't save the Dinner Recipe")
      intercept[InvalidRecipeTitleException] {
        service create(recipe)
      }

      val order = Mockito.inOrder(dinnerRecipeRepository)
      order verify dinnerRecipeRepository findByTitle recipe.getTitle

    }
  }

  feature("Update a Dinner Recipe") {

    scenario("Updating a valid existing Dinner Recipe") {

      Given("an existing Dinner Recipe")
      val recipe:DinnerRecipe = chocolateCakeRecipe()
      existingRecipe(recipe);

      And("with a valid recipe title")
      validRecipeWithGivenTitle(recipe)

      Then("should update the Dinner Recipe")
      service update(recipe getId, recipe)

      val order = Mockito.inOrder(dinnerRecipeRepository, itemRepository)
      order verify dinnerRecipeRepository findOne recipe.getId
      order verify dinnerRecipeRepository findByTitle recipe.getTitle
      order verify itemRepository save recipe.getItems
      order verify dinnerRecipeRepository save recipe

    }

    scenario("Trying to update a Dinner Recipe with an given already existing title") {

      Given("an existing Dinner Recipe")
      val recipe:DinnerRecipe = bollerIKarryRecipe()
      existingRecipe(recipe);

      And("with a given already existing recipe title")
      Mockito.when(dinnerRecipeRepository.findByTitle(recipe getTitle))
        .thenReturn(chocolateCakeRecipe())

      Then("shouldn't update the Dinner Recipe")
      intercept[InvalidRecipeTitleException] {
        service update(recipe getId, recipe)
      }

      val order = Mockito.inOrder(dinnerRecipeRepository)
      order verify dinnerRecipeRepository findOne recipe.getId
      order verify dinnerRecipeRepository findByTitle recipe.getTitle

    }

  }

  feature("Find Dinner Recipes") {

    scenario("Trying to find an non existing Dinner Recipe") {

      Given("An non existing Dinner Recipe id")
      Then("shouldn't find the result with the given parameters")
      intercept[RecipeNotFoundException] {
        service get("abc")
      }

      val order = Mockito.inOrder(dinnerRecipeRepository)
      order verify dinnerRecipeRepository findOne "abc"

    }

  }

  feature("Delete a Dinner Recipe") {

    scenario("Trying to delete a non existing Dinner Recipe") {

      Given("An non existing Dinner Recipe id")
      Then("shouldn't delete with the given parameters")
      intercept[RecipeNotFoundException] {
        service delete("id")
      }

      val order = Mockito.inOrder(dinnerRecipeRepository)
      order verify dinnerRecipeRepository findOne "id"

    }

  }

  def bollerIKarryRecipe():DinnerRecipe = Fixture.from(classOf[DinnerRecipe]).gimme("bollerIKarry")
  def chocolateCakeRecipe():DinnerRecipe = Fixture.from(classOf[DinnerRecipe]).gimme("chocolateCake")
  def validRecipeWithGivenTitle(recipe:DinnerRecipe) = Mockito.when(dinnerRecipeRepository.findByTitle(recipe getTitle))
    .thenReturn(recipe)
  def existingRecipe(recipe:DinnerRecipe) = Mockito.when(dinnerRecipeRepository.findOne(recipe getId))
    .thenReturn(recipe)

}