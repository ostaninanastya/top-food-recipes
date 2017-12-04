package com.topfood.recipes.ingredientRecipe.service;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.ingredient.repository.IngredientRepository;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.repository.IngredientRecipeRepository;
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

    public IngredientRecipe getByID(String id)
    {
        return ingredientRecipeRepository.findOne(Long.valueOf(id));
    }

    public ErrorCodes add(IngredientRecipe ingredientRecipe){
        ingredientRecipeRepository.save(ingredientRecipe);
        return (OK);
    }
    public void delete(String id){
        ingredientRecipeRepository.delete(Long.valueOf(id));
    }

    public void update(IngredientRecipe newIngredientRecipe)
    {
        IngredientRecipe ingredientRecipe = ingredientRecipeRepository.findOne(newIngredientRecipe.getId());
        ingredientRecipe.setRecipe(newIngredientRecipe.getRecipe());
        ingredientRecipe.setIngredient(newIngredientRecipe.getIngredient());
        ingredientRecipe.setQuantity(newIngredientRecipe.getQuantity());
        recipeRepository.flush();
    }
}
