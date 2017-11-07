package com.topfood.recipes.ingredient.repository;

import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.measure.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByName(String name);
}
