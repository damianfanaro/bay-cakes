package com.damianfanaro.baycakes.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@RestController
@RequestMapping("/recipe")
@PreAuthorize("@securityService.hasProtectedAccess()")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping(value = "/{recipeName}")
    public Recipe getRecipeByName(@PathVariable String recipeName) {
        return recipeService.getRecipeByName(recipeName);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

}
