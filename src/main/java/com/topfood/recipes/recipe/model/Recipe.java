package com.topfood.recipes.recipe.model;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.user.model.User;

import javax.persistence.*;

@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="recipe_id")
    private Long recipe_id;

    @Column(name="recipe")
    private String recipe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    public Recipe(){

    }

    public Recipe(String recipe, User user, Cuisine cuisine) {
        this.recipe = recipe;
        this.user = user;
        this.cuisine = cuisine;
    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
