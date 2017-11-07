package com.topfood.recipes.ingredientRecipe.repository;

import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {
}
