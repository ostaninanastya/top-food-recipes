package com.topfood.recipes.recipe.controller;

import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.service.RecipeService;
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
@RequestMapping("/api/recipe")
public class RecipeRestController {
    @Autowired
    private RecipeService recipeService;

    @ApiOperation(value = "Get all recipes", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @ApiOperation(value = "Get recipe by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Recipe> getRecipe(@ApiParam(value = "Recipe id", required = true) @PathVariable String id) {
        Recipe recipe = recipeService.getByID(id);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a recipe", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        recipeService.add(recipe);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a recipe", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
        recipeService.update(recipe);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a recipe", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Recipe> deleteRecipe(@ApiParam(value = "Recipe id", required = true) @PathVariable String id) {
        recipeService.delete(id);
        return new ResponseEntity<Recipe>(HttpStatus.OK);
    }

}
