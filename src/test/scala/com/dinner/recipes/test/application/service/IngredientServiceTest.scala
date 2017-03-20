package com.dinner.recipes.test.application.service

import br.com.six2six.fixturefactory.Fixture
import com.dinner.recipes.application.service.provider.IngredientServiceProvider
import com.dinner.recipes.application.service.{IngredientService, ItemService}
import com.dinner.recipes.domain.model.{DinnerRecipe, Ingredient, Item}
import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException
import com.dinner.recipes.test.support.ScalaTestSupport
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IngredientServiceTest extends ScalaTestSupport {

  private val itemService:ItemService = mock[ItemService]
  private val service:IngredientService = new IngredientServiceProvider(itemService)

  feature("Add new Ingredients into Dinner Recipe Items") {

    scenario("Adding a valid Ingredient into an existing Dinner Recipe Item") {

      Given("a valid Ingredient")
      val ingredient:Ingredient = poundGroundPorkIngredient()

      And("the Item of the given Ingredient is valid")
      val item:Item = currySauceItem()
      validExistingItem(item)

      And("The Dinner Recipe of the given is valid")
      val recipe:DinnerRecipe = bollerIKarryRecipe()

      Then("should add the Ingredient into given Item")
      service.addIngredient(recipe getId, item getId, ingredient)

      val order = Mockito.inOrder(itemService)
      order verify itemService get item.getId
      order verify itemService update(recipe getId, item getId, item)

    }

    scenario("Trying to add a valid Ingredient into a valid Dinner Recipe Item that already contains that Ingredient") {

      Given("a valid Ingredient")
      val ingredient:Ingredient = poundGroundPorkIngredient()

      And("the Item of the given Ingredient is valid")
      And("the Item contains the given Ingredient")
      val item:Item = meatBallsItem()
      validExistingItem(item)

      And("The Dinner Recipe of the given is valid")
      val recipe:DinnerRecipe = bollerIKarryRecipe()

      Then("shouldn't add the Ingredient into given Item")
      intercept[InvalidDinnerRecipeParameterException] {
        service.addIngredient(recipe getId, item getId, ingredient)
      }

      val order = Mockito.inOrder(itemService)
      order verify itemService get item.getId

    }

  }

  feature("Remove Ingredients from Dinner Recipe Items") {

    scenario("Removing a valid Ingredient from an existing Dinner Recipe Item") {

      Given("a valid Ingredient")
      val ingredient:Ingredient = tablespoonsYellowCurryIngredient()

      And("the Item of the given Ingredient is valid")
      And("the Item contains the given Ingredient")
      val item:Item = currySauceItem()
      validExistingItem(item)

      And("The Dinner Recipe of the given is valid")
      val recipe:DinnerRecipe = bollerIKarryRecipe()

      Then("should remove the Ingredient into given Item")
      service.removeIngredient(recipe getId, item getId, ingredient)

      val order = Mockito.inOrder(itemService)
      order verify itemService get item.getId
      order verify itemService update(recipe getId, item getId, item)

    }

    scenario("Trying to remove a valid Ingredient from a valid Dinner Recipe Item that does not contains that Ingredient") {

      Given("a valid Ingredient")
      val ingredient:Ingredient = tablespoonsYellowCurryIngredient()

      And("the Item of the given Ingredient is valid")
      And("the Item contains the given Ingredient")
      val item:Item = meatBallsItem()
      validExistingItem(item)

      And("The Dinner Recipe of the given is valid")
      val recipe:DinnerRecipe = bollerIKarryRecipe()

      Then("should remove the Ingredient into given Item")
      intercept[InvalidDinnerRecipeParameterException] {
        service.removeIngredient(recipe getId, item getId, ingredient)
      }

      val order = Mockito.inOrder(itemService)
      order verify itemService get item.getId

    }

  }

  def bollerIKarryRecipe():DinnerRecipe = Fixture.from(classOf[DinnerRecipe]).gimme("bollerIKarry")
  def meatBallsItem():Item = Fixture.from(classOf[Item]).gimme("meatballs")
  def currySauceItem():Item = Fixture.from(classOf[Item]).gimme("currySauce")
  def forChocolateCake():Item = Fixture.from(classOf[Item]).gimme("forTheChocolateCake")
  def poundGroundPorkIngredient():Ingredient = Fixture.from(classOf[Ingredient]).gimme("poundGroundPork")
  def tablespoonsYellowCurryIngredient():Ingredient = Fixture.from(classOf[Ingredient]).gimme("tablespoonsYellowCurry")
  def validExistingItem(item:Item) = Mockito.when(itemService get(item getId))
    .thenReturn(item)

}