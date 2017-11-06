package com.topfood.recipes.ingredient.controller;

import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.ingredient.service.IngredientService;
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
@RequestMapping("/api/ingredient")
public class IngredientRestController {
    @Autowired
    private IngredientService ingredientService;

    @ApiOperation(value = "Get all ingredients", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.findAll();
        return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
    }

    @ApiOperation(value = "Get ingredient by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ingredient> getIngredient(@ApiParam(value = "Ingredient id", required = true) @PathVariable String id) {
        Ingredient ingredient = ingredientService.getByID(id);
        return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a ingredient", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.add(ingredient);
        return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a ingredient", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.update(ingredient);
        return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a ingredient", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Ingredient> deleteIngredient(@ApiParam(value = "Ingredient id", required = true) @PathVariable String id) {
        ingredientService.delete(id);
        return new ResponseEntity<Ingredient>(HttpStatus.OK);
    }

}
