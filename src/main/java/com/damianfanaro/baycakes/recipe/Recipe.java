package com.damianfanaro.baycakes.recipe;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

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

}
