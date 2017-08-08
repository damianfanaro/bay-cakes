package com.damianfanaro.baycakes.recipe;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    Recipe findByRecipeName(String recipeName);

}
