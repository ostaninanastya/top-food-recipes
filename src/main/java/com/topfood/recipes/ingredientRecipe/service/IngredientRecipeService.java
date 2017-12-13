package com.topfood.recipes.ingredientRecipe.service;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.ingredient.repository.IngredientRepository;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.repository.IngredientRecipeRepository;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;

@Service
public class IngredientRecipeService {

    @Autowired
    private IngredientRecipeRepository ingredientRecipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<IngredientRecipe> findAll() {
        return ingredientRecipeRepository.findAll();
    }

    public List<IngredientRecipe> findByRecipe(Recipe recipe) {
        return ingredientRecipeRepository.findByRecipe(recipe);
    }

    public IngredientRecipe getByID(String id)
    {
        return ingredientRecipeRepository.findOne(Long.valueOf(id));
    }

    public ErrorCodes add(IngredientRecipe ingredientRecipe){
        ingredientRecipeRepository.save(ingredientRecipe);
        return (OK);
    }
    public ErrorCodes delete(IngredientRecipe ingredientRecipe){
        ingredientRecipeRepository.delete(ingredientRecipe);
        return(OK);
    }

    public ErrorCodes update(IngredientRecipe newIngredientRecipe)
    {
        IngredientRecipe ingredientRecipe = ingredientRecipeRepository.findOne(newIngredientRecipe.getId());
        ingredientRecipe.setRecipe(newIngredientRecipe.getRecipe());
        ingredientRecipe.setIngredient(newIngredientRecipe.getIngredient());
        ingredientRecipe.setQuantity(newIngredientRecipe.getQuantity());
        recipeRepository.flush();
        return (OK);
    }
}
