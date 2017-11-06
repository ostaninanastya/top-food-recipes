package com.topfood.recipes.ingredient.service;

import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.ingredient.repository.IngredientRepository;
import com.topfood.recipes.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    //@Autowired
  //  private MeusureRepository meusureRepository;

    @PostConstruct
    public void init() {
  //      ingredientRepository.save(new Ingredient("томат", meusureRepository.findOne(1)));
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getByID(String ingredient_id)
    {
        return ingredientRepository.findOne(Long.valueOf(ingredient_id));
    }

    public void add(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
    public void delete(String ingredient_id){
        ingredientRepository.delete(Long.valueOf(ingredient_id));
    }

    public void update(Ingredient newIngredient)
    {
        Ingredient ingredient = ingredientRepository.findOne(newIngredient.getIngredient_id());
        ingredient.setIngredient_name(newIngredient.getIngredient_name());
//        ingredient.setMeasure(newIngredient.getMeasure());
        ingredientRepository.flush();
    }

}
