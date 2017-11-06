package com.topfood.recipes.recipe.repository;

import com.topfood.recipes.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
