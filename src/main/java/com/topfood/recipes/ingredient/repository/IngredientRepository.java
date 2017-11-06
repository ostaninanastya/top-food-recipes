package com.topfood.recipes.ingredient.repository;

import com.topfood.recipes.ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
