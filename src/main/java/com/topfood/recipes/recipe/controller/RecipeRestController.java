package com.topfood.recipes.recipe.controller;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.service.CuisineService;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.service.RecipeService;
import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/recipe")
public class RecipeRestController {
    //private static final Logger LOG = LoggerFactory.getLogger(RecipeRestController.class);
    private static String UPLOADED_FOLDER = "/var/www/html/topfoodrecipes";
    @Autowired
    private RecipeService recipeService;
    private CuisineService cuisineService;
    private UserService userService;

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

    //@ApiOperation(value = "Upload image", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/img", method = POST)
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String recipe, @RequestParam Long user_id, @RequestParam Long cuisine_id) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER+file.getOriginalFilename());
        Files.write(path, bytes);
        Cuisine cuisine = cuisineService.getByID(Long.toString(cuisine_id));
        User user = userService.getByID(Long.toString(user_id));
        Recipe newRecipe = new Recipe(name, recipe,user, cuisine);
        newRecipe.setImage("http://188.166.30.145/topfoodrecipes/"+file.getOriginalFilename());
        recipeService.add(newRecipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }


   /* @ApiOperation(value = "Create a recipe with the picture", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<Recipe> createRecipeImg (@RequestBody Recipe recipe, @RequestParam MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER+file.getOriginalFilename());
        Files.write(path, bytes);
        recipeService.add(recipe);
        //recipe.setImgPath("http://188.166.30.145/topfoodrecipes/"+file.getOriginalFilename());
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }
*/

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
