package com.topfood.recipes.ingredientRecipe.controller;


import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import com.topfood.recipes.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.service.IngredientRecipeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/ingredientRecipe")
public class IngredientRecipeRestController {

    @Autowired
    private IngredientRecipeService ingredientRecipeService;

    @ApiOperation(value = "Get all ingredientsRecipe for one recipe", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/getByRecipe", method = POST)
    public ResponseEntity<List<IngredientRecipe>> getAllIngredientRecipes(@RequestBody Recipe recipe) {
        List<IngredientRecipe> ingredientRecipes = ingredientRecipeService.findByRecipe(recipe);
        return new ResponseEntity<List<IngredientRecipe>>(ingredientRecipes, HttpStatus.OK);
    }

    @ApiOperation(value = "Get ingredients in recipes by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<IngredientRecipe> getIngredientRecipe(@ApiParam(value = "id", required = true) @PathVariable String id) {
        IngredientRecipe ingredientRecipe = ingredientRecipeService.getByID(id);
        return new ResponseEntity<IngredientRecipe>(ingredientRecipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Create ingredientsRecipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<? extends Object> createIngredientRecipe(@RequestBody IngredientRecipe[] ingredientRecipeArray) {

        for (IngredientRecipe ingredientRecipe: ingredientRecipeArray) {
            ErrorCodes code = ingredientRecipeService.add(ingredientRecipe);

            if (!code.equals(ErrorCodes.OK)) {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<IngredientRecipe[]>(ingredientRecipeArray, HttpStatus.OK);
    }

    @ApiOperation(value = "Update ingredients of recipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = PUT)
    public ResponseEntity<? extends Object> updateIngredientRecipe(@RequestBody IngredientRecipe[] ingredientRecipeArray) {
        for (IngredientRecipe ingredientRecipe: ingredientRecipeArray) {
            ErrorCodes code;
            if (ingredientRecipe.getId()==null)
            {
                code = ingredientRecipeService.add(ingredientRecipe);
            }
            else code = ingredientRecipeService.update(ingredientRecipe);

            if (!code.equals(ErrorCodes.OK)) {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<IngredientRecipe[]>(ingredientRecipeArray, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete ingredientsRecipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/delete", method = PUT)
    public ResponseEntity<? extends Object> deleteIngredientRecipe(@RequestBody IngredientRecipe[] ingredientRecipeDeleteArray) {
        for (IngredientRecipe ingredientRecipe: ingredientRecipeDeleteArray) {
            ErrorCodes code = ingredientRecipeService.delete(ingredientRecipe);
            if (!code.equals(ErrorCodes.OK)) {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        }
        return new  ResponseEntity<IngredientRecipe[]>(ingredientRecipeDeleteArray, HttpStatus.OK);
    }
}
