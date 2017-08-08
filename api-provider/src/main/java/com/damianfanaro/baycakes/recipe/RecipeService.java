package com.damianfanaro.baycakes.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Service
public class RecipeService {

    private final RecipeRepository repository;

    @Autowired
    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    Recipe getRecipeByName(String recipeName) {
        return repository.findByRecipeName(recipeName);
    }

    Recipe addRecipe(Recipe recipe) {
        return repository.insert(recipe);
    }

}
