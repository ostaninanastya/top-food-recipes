package com.topfood.recipes.ingredient.service;

import com.topfood.recipes.common.Enums.ErrorCodes;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.ingredient.repository.IngredientRepository;
import com.topfood.recipes.measure.repository.MeasureRepository;
import com.topfood.recipes.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.topfood.recipes.common.Enums.ErrorCodes.OK;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MeasureRepository measureRepository;


    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getByID(String ingredient_id)
    {
        return ingredientRepository.findOne(Long.valueOf(ingredient_id));
    }

    public ErrorCodes add(Ingredient ingredient){
        ingredientRepository.save(ingredient);
        return (OK);
    }
    public void delete(String ingredient_id){
        ingredientRepository.delete(Long.valueOf(ingredient_id));
    }

    public void update(Ingredient newIngredient)
    {
        Ingredient ingredient = ingredientRepository.findOne(newIngredient.getIngredient_id());
        ingredient.setName(newIngredient.getName());
        ingredient.setMeasure(newIngredient.getMeasure());
        ingredientRepository.flush();
    }

}
