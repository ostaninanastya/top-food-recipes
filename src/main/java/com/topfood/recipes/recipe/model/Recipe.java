package com.topfood.recipes.recipe.model;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.like.model.Like;
import com.topfood.recipes.user.model.User;

import javax.persistence.*;

@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="recipe_id")
    private Long recipe_id;

    @Column(name ="name")
    private String name;

    @Column(name="recipe", length = 10000)
    private String recipe;

    @Column(name ="image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @Column(name="rating")
    private Integer rating;

    public Recipe(){

    }

    public Recipe(String name, String recipe, String image, User user, Cuisine cuisine) {
        this.name = name;
        this.recipe = recipe;
        this.image = image;
        this.user = user;
        this.cuisine = cuisine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "id: " + getRecipe_id()
                + "name: " + getName()
                + "recipe: " + getRecipe()
                + "cuisine: " + getCuisine().getName();
    }
}
