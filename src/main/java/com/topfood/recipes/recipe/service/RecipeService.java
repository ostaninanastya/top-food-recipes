package com.topfood.recipes.recipe.service;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.repository.RecipeRepository;
import com.topfood.recipes.user.repository.UserRepository;

@Service
public class RecipeService {
    private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    @Value("${upload.folder}")
    private String uploadFolder;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe getByID(String recipe_id)
    {
        return recipeRepository.findOne(Long.valueOf(recipe_id));
    }

    public ErrorCodes add(Recipe recipe){
        recipeRepository.save(recipe);
        recipeRepository.flush();
        return (OK);
    }
    public void delete(String recipe_id){
        recipeRepository.delete(Long.valueOf(recipe_id));
    }

    public void update(Recipe newRecipe)
    {
        Recipe recipe = recipeRepository.findOne(newRecipe.getRecipe_id());
        LOG.info("Old recipe: " + recipe);
        LOG.info("New recipe: " + newRecipe);
        recipe.setName(newRecipe.getName());
        recipe.setCuisine(newRecipe.getCuisine());
        recipe.setRecipe(newRecipe.getRecipe());
        recipe.setUser(newRecipe.getUser());
        recipeRepository.flush();
    }

    public void storeFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadFolder + file.getOriginalFilename());
        Files.write(path, bytes);
    }
}
