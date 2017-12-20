package com.topfood.recipes.ingredientRecipe.model;


import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.recipe.model.Recipe;

import javax.persistence.*;

@Entity
@Table(name="IngredientRecipe")
public class IngredientRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long id;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public IngredientRecipe () {

    }

    public IngredientRecipe (Ingredient ingredient, Recipe recipe, Double quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
