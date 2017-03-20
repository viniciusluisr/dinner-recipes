package com.dinner.recipes.domain.repository;

import com.dinner.recipes.domain.model.DinnerRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DinnerRecipeRepository extends MongoRepository<DinnerRecipe, String> {

    DinnerRecipe findByTitle(final String title);
}
