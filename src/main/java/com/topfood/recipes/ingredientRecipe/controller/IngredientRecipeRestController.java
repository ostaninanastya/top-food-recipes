package com.topfood.recipes.ingredientRecipe.controller;


import com.topfood.recipes.common.enums.ErrorCodeMap;
import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.service.IngredientRecipeService;
import com.topfood.recipes.recipe.repository.RecipeRepository;
import com.topfood.recipes.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/ingredientRecipe")
public class IngredientRecipeRestController {

    @Autowired
    private IngredientRecipeService ingredientRecipeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @ApiOperation(value = "Get all ingredients in recipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<IngredientRecipe>> getAllIngredientRecipes() {
        List<IngredientRecipe> ingredientRecipes = ingredientRecipeService.findAll();
        return new ResponseEntity<List<IngredientRecipe>>(ingredientRecipes, HttpStatus.OK);
    }

    @ApiOperation(value = "Get ingredients in recipes by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<IngredientRecipe> getIngredientRecipe(@ApiParam(value = "id", required = true) @PathVariable String id) {
        IngredientRecipe ingredientRecipe = ingredientRecipeService.getByID(id);
        return new ResponseEntity<IngredientRecipe>(ingredientRecipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Create ingredients of recipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<? extends Object> createIngredientRecipe(@RequestBody IngredientRecipe[] ingredientRecipeArray) {

        for (IngredientRecipe ingredientRecipe: ingredientRecipeArray) {
            if (ingredientRecipe.getRecipe().getUser() != null) ingredientRecipe.getRecipe().getUser().setUser_id(userRepository.findByName(ingredientRecipe.getRecipe().getUser().getName()).get(0).getUser_id());
            ingredientRecipe.getRecipe().setRecipe_id(recipeRepository.findByName(ingredientRecipe.getRecipe().getName()).get(0).getRecipe_id());
            ErrorCodes code = ingredientRecipeService.add(ingredientRecipe);
            if (!code.equals(ErrorCodes.OK))
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

            return new ResponseEntity<IngredientRecipe[]>(ingredientRecipeArray, HttpStatus.OK);
    }

    @ApiOperation(value = "Update ingredients of recipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<IngredientRecipe> updateIngredientRecipe(@RequestBody IngredientRecipe ingredientRecipe) {
        ingredientRecipeService.update(ingredientRecipe);
        return new ResponseEntity<IngredientRecipe>(ingredientRecipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete ingredients of recipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<IngredientRecipe> deleteIngredientRecipe(@ApiParam(value = "id", required = true) @PathVariable String id) {
        ingredientRecipeService.delete(id);
        return new ResponseEntity<IngredientRecipe>(HttpStatus.OK);
    }
}
