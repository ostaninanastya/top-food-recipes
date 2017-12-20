package com.topfood.recipes.recipe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.repository.IngredientRecipeRepository;
import com.topfood.recipes.like.service.LikeService;
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

    @Autowired
    private LikeService likeService;

    @Autowired
    private IngredientRecipeRepository ingredientRecipeRepository;

    public List<Recipe> findAll() {

        List <Recipe> recipes = recipeRepository.findAll();
        for (Recipe r: recipes)
        {
            r.setRating(likeService.Rating(String.valueOf(r.getRecipe_id())));
            recipeRepository.save(r);
        }
        return recipes;
    }

    public Recipe getByID(String recipe_id)
    {
        Recipe recipe = recipeRepository.findOne(Long.valueOf(recipe_id));
        recipe.setRating(likeService.Rating(recipe_id));
        recipeRepository.save(recipe);
        return recipe;
    }

    public Recipe add(Recipe recipe){
        Recipe recipeSaved = recipeRepository.save(recipe);
        recipeRepository.flush();
        return recipeSaved;
    }
    public void delete(String recipe_id){
        List<IngredientRecipe> ingredientRecipes = ingredientRecipeRepository.findByRecipe(recipeRepository.findOne(Long.valueOf(recipe_id)));
        for (IngredientRecipe i : ingredientRecipes)
            ingredientRecipeRepository.delete(i.getId());
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

    public void storeFile(MultipartFile file, Recipe recipe) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadFolder + recipe.getName()+ "_" + file.getOriginalFilename());
        Files.write(path, bytes);
        Runtime.getRuntime().exec("chmod -R 755 " + uploadFolder);
    }

    public void storeFile(File file, Recipe recipe) throws IOException {
        byte[] bytes = fileToByteArray(file);
        Path path = Paths.get(uploadFolder + recipe.getName()+ "_" + file.getName());
        Files.write(path, bytes);
        Runtime.getRuntime().exec("chmod -R 755 " + uploadFolder);
    }

    public byte[] fileToByteArray(File file) throws IOException {

        byte[] buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            if (ios.read(buffer) == -1) {
                throw new IOException(
                        "EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return buffer;
    }

    public Page<Recipe> findPaginated(int page, int size) {
        return recipeRepository.findAll(new PageRequest(page, size));
    }
}
