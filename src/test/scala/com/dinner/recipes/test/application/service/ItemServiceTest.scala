package com.dinner.recipes.test.application.service

import br.com.six2six.fixturefactory.Fixture
import com.dinner.recipes.application.service.DinnerRecipeService
import com.dinner.recipes.application.service.provider.ItemServiceProvider
import com.dinner.recipes.domain.model.{DinnerRecipe, Item}
import com.dinner.recipes.domain.repository.ItemRepository
import com.dinner.recipes.infra.exception.{InvalidDinnerRecipeParameterException, RecipeItemNotFoundException}
import com.dinner.recipes.test.support.ScalaTestSupport
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ItemServiceTest extends ScalaTestSupport {

  private val dinnerRecipeService:DinnerRecipeService = mock[DinnerRecipeService]
  private val itemRepository:ItemRepository = mock[ItemRepository]
  private val service:ItemServiceProvider = new ItemServiceProvider(dinnerRecipeService, itemRepository)

  feature("Add new Items into Dinner Recipes") {

    scenario("Adding a valid Item into an existing Dinner Recipe") {

      Given("a valid Dinner Recipe Item")
      val item: Item = meatBallsItem()

      And("the Dinner Recipe of the given Item is valid")
      val recipe: DinnerRecipe = chocolateCakeRecipe()
      validRecipeWithGivenId(recipe)

      Then("should update the Dinner Recipe with the given Item")
      service.addItem(recipe getId, item)

      val order = Mockito.inOrder(dinnerRecipeService)
      order verify dinnerRecipeService get recipe.getId
      order verify dinnerRecipeService update(recipe getId, recipe)

    }

    scenario("Trying to add a valid Item into an existing Dinner Recipe that already has that Item") {

      Given("a valid Dinner Recipe Item")
      val item: Item = meatBallsItem()

      And("the Dinner Recipe of the given Item is valid")
      And("the Dinner Recipe alrady contains the given Item")
      val recipe: DinnerRecipe = bollerIKarryRecipe
      validRecipeWithGivenId(recipe)

      Then("should update the Dinner Recipe with the given Item")
      intercept[InvalidDinnerRecipeParameterException] {
        service addItem(recipe getId, item)
      }

      val order = Mockito.inOrder(dinnerRecipeService)
      order verify dinnerRecipeService get recipe.getId

    }

  }

  feature("Remove Items from Dinner Recipes") {

    scenario("Removing a valid Item from an existing Dinner Recipe") {

      Given("a valid Dinner Recipe Item")
      val item: Item = forChocolateCake()

      And("the Dinner Recipe of the given Item is valid")
      val recipe: DinnerRecipe = chocolateCakeRecipe()
      validRecipeWithGivenId(recipe)

      And("the given Item already exists")
      existingItem(item)

      Then("should remove the given Item from Dinner Recipe")
      service removeItem(recipe getId, item getId)

      val order = Mockito.inOrder(dinnerRecipeService, itemRepository)
      order verify dinnerRecipeService get recipe.getId
      order verify itemRepository findOne(item getId)
      order verify itemRepository delete(item getId)
      order verify dinnerRecipeService update(recipe getId, recipe)

    }

    scenario("Trying to remove a valid Item from a non existing Dinner Recipe") {

      Given("a valid Dinner Recipe Item")
      val item: Item = meatBallsItem()

      And("and the given Dinner Recipe does not exists")
      Then("shoudn't remove the given Item from Dinner Recipe")
      intercept[RecipeItemNotFoundException] {
        service removeItem("id", item getId)
      }

      val order = Mockito.inOrder(dinnerRecipeService)
      order verify dinnerRecipeService get "id"

    }

    scenario("Trying to remove a valid Item from an existing Dinner Recipe that does not contains that Item") {

      Given("a valid Dinner Recipe Item")
      val item: Item = meatBallsItem()

      And("the Dinner Recipe of the given Item is valid")
      val recipe: DinnerRecipe = chocolateCakeRecipe()
      validRecipeWithGivenId(recipe)

      And("the given Item already exists")
      existingItem(item)

      And("the Dinner Recipe does not contains the given Item")
      Then("shouldn't remove the given Item from Dinner Recipe")
      intercept[InvalidDinnerRecipeParameterException] {
        service removeItem(recipe getId, item getId)
      }

      val order = Mockito.inOrder(dinnerRecipeService, itemRepository)
      order verify dinnerRecipeService get recipe.getId
      order verify itemRepository findOne(item getId)

    }

  }

  feature("Update Dinner Recipe Items") {

    scenario("Updating a valid Dinner Recipe Item") {

      Given("a valid Dinner Recipe Item")
      val item: Item = meatBallsItem()

      And("the Dinner Recipe of the given Item is valid")
      val recipe: DinnerRecipe = bollerIKarryRecipe()
      validRecipeWithGivenId(recipe)

      And("the given Item already exists")
      existingItem(item)

      Then("should update the given Item")
      service update(recipe getId, item getId, item)

      val order = Mockito.inOrder(dinnerRecipeService, itemRepository)
      order verify dinnerRecipeService get recipe.getId
      order verify itemRepository findOne(item getId)
      order verify dinnerRecipeService update(recipe getId, recipe)

    }

    scenario("Trying to update a valid Item from an existing Dinner Recipe that does not contains that Item") {

      Given("a valid Dinner Recipe Item")
      val item: Item = meatBallsItem()

      And("the Dinner Recipe of the given Item is valid")
      val recipe: DinnerRecipe = chocolateCakeRecipe()
      validRecipeWithGivenId(recipe)

      And("the given Item already exists")
      existingItem(item)

      And("The Dinner Recipe does not contains the given Item")
      Then("shouldn't update the given Item")
      intercept[RecipeItemNotFoundException] {
        service update(recipe getId, item getId, item)
      }

      val order = Mockito.inOrder(dinnerRecipeService)
      order verify dinnerRecipeService get(recipe getId)

    }

  }

  feature("Find Dinner Recipe Items") {

    scenario("Trying to find an non existing Dinner Recipe Item") {

      Given("An non existing Item id")
      Then("shouldn't find the result with the given parameters")
      intercept[RecipeItemNotFoundException] {
        service get("id")
      }

      val order = Mockito.inOrder(itemRepository)
      order verify itemRepository findOne "id"

    }

  }

  def bollerIKarryRecipe():DinnerRecipe = Fixture.from(classOf[DinnerRecipe]).gimme("bollerIKarry")
  def chocolateCakeRecipe():DinnerRecipe = Fixture.from(classOf[DinnerRecipe]).gimme("chocolateCake")
  def validRecipeWithGivenId(recipe:DinnerRecipe) = Mockito.when(dinnerRecipeService.get(recipe getId))
    .thenReturn(recipe)
  def meatBallsItem():Item = Fixture.from(classOf[Item]).gimme("meatballs")
  def currySauceItem():Item = Fixture.from(classOf[Item]).gimme("currySauce")
  def forChocolateCake():Item = Fixture.from(classOf[Item]).gimme("forTheChocolateCake")
  def existingItem(item:Item) = Mockito.when(itemRepository.findOne(item getId))
    .thenReturn(item)

}