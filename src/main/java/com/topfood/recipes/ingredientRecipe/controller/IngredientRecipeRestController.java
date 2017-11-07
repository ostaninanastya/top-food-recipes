package com.topfood.recipes.ingredientRecipe.controller;


import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.service.IngredientRecipeService;
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
    public ResponseEntity<IngredientRecipe> createIngredientRecipe(@RequestBody IngredientRecipe ingredientRecipe) {
        ingredientRecipeService.add(ingredientRecipe);
        return new ResponseEntity<IngredientRecipe>(ingredientRecipe, HttpStatus.OK);
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
