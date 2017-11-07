package com.topfood.recipes.recipe.repository;

import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
}
