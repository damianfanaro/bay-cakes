package com.damianfanaro.baycakes.config;

import com.damianfanaro.baycakes.recipe.Recipe;
import com.damianfanaro.baycakes.recipe.RecipeRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Configuration
public class MongoInitialization {

    private final RecipeRepository recipeRepository;

    @Autowired
    public MongoInitialization(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Bean
    CommandLineRunner preLoadMongoCollections() throws Exception {
        return args -> {
            recipeRepository.deleteAll();
            Recipe recipe = Recipe.of("cake", Lists.newArrayList("egg", "milk"));
            recipe.setId("59881440c0d275035ac8817f");
            recipeRepository.insert(recipe);
            //recipeRepository.insert(Recipe.of("muffin", Lists.newArrayList("flour", "chocolate")));
        };
    }

}
