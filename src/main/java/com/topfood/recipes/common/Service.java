package com.topfood.recipes.common;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.ingredient.repository.IngredientRepository;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.repository.IngredientRecipeRepository;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.measure.repository.MeasureRepository;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.repository.RecipeRepository;
import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private CuisineRepository cuisineRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private IngredientRecipeRepository ingredientRecipeRepository;
    @Autowired
    private MeasureRepository measureRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;
    @PostConstruct
    public void init()
    {
        userRepository.save(new User("admin", "admin"));
        User adminUser = userRepository.findByName("admin").get(0);
        String strPizzaRecipe = "Рецепт пиццы";
        cuisineRepository.save(new Cuisine("Американская"));
        cuisineRepository.save(new Cuisine("Русская"));
        cuisineRepository.save(new Cuisine("Китайская"));
        cuisineRepository.save(new Cuisine("Японская"));
        cuisineRepository.save(new Cuisine("Итальянская"));

        measureRepository.save(new Measure("кг."));
        Measure kg = measureRepository.findByName("кг.").get(0);
        measureRepository.save(new Measure("г."));
        Measure g = measureRepository.findByName("г.").get(0);
        measureRepository.save(new Measure("л."));
        Measure l = measureRepository.findByName("л.").get(0);
        measureRepository.save(new Measure("стакан"));
        Measure glass = measureRepository.findByName("стакан").get(0);
        measureRepository.save(new Measure("ст. ложка"));
        measureRepository.save(new Measure("ч. ложка"));
        measureRepository.save(new Measure("мл."));
        measureRepository.save(new Measure("штука"));

        ingredientRepository.save(new Ingredient("томат", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("сахар", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("молоко", measureRepository.findByName("стакан").get(0)));
        ingredientRepository.save(new Ingredient("сахар", measureRepository.findByName("ч. ложка").get(0)));

        recipeRepository.save(new Recipe(strPizzaRecipe, "YUIODFGHJKSDFGYUIDFGHJCVBNM<",adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        //recipeRepository.save(new Recipe("Рецепт пасты", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe pizzaRecipe = recipeRepository.findByName(strPizzaRecipe).get(0);

        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("молоко").get(0), pizzaRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), pizzaRecipe, 1));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("томат").get(0), pizzaRecipe , 1));

    }
}
