package com.topfood.recipes.ingredientRecipe.repository;

import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {
    List<IngredientRecipe> findByRecipe(Recipe recipe);
}
