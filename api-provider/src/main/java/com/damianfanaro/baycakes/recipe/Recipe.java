package com.damianfanaro.baycakes.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Recipe {

    @Id
    private String id;

    private String recipeName;
    private List<String> ingredients;

    public Recipe(String recipeName, List<String> ingredients) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    public static Recipe of(String recipeName, List<String> ingredients) {
        Objects.requireNonNull(recipeName, "Recipe name cannot be null!");
        return new Recipe(recipeName, ingredients);
    }

}
