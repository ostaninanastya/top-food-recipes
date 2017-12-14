package com.topfood.recipes.common;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.ingredient.model.Ingredient;
import com.topfood.recipes.ingredient.repository.IngredientRepository;
import com.topfood.recipes.ingredientRecipe.model.IngredientRecipe;
import com.topfood.recipes.ingredientRecipe.repository.IngredientRecipeRepository;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.measure.repository.MeasureRepository;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.repository.RecipeRepository;
import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import com.topfood.recipes.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.mock.web.MockMultipartFile;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private CuisineRepository cuisineRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private IngredientRecipeRepository ingredientRecipeRepository;
    @Autowired
    private MeasureRepository measureRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeService recipeService;
    @Value("${image.prefix}")
    private String imagePrefix;

    @PostConstruct
    public void init() {
        userRepository.save(new User("admin", "admin"));
        User adminUser = userRepository.findByName("admin").get(0);
        String namePizzaRecipe = "Пицца маргарита";
        String namePastaRecipe = "Паста карбонара с беконом";
        String nameBurgerRecipe = "Бургер с курицей";
        String nameSoupRecipe = "Борщ";
        String nameCheeseSoupRecipe = "Сырный суп";
        String nameSushiCalifornia = "Суши Калифорния";
        String nameHawaiianPizza = "Пицца Гавайская";
        String nameUdon = "Удон с курицей";
        String nameCake = "Торт Прага";

        String recipePizzaStr = "Хорошее тесто для пиццы можно приготовить, смешав четыре стакана муки с двумя упаковками сухих дрожжей, четвертью стакана оливкового масла, двумя столовыми ложками сахара, двумя чайными ложки крупной соли и добавив примерно полтора стакана теплой воды.";
        String recipePastaStr = "Разогреваем на сковороде оливковое масло, обжариваем измельченный чеснок. Добавляем нарезанную кубиками ветчину и жарим 3 минуты.\n" +
                "Взбиваем сливки с желтками, добавляем пармезан, соль и перец по вкусу.\n" +
                "Варим спагетти. Кидаем их в сковороду с ветчиной.";
        String recipeBurgerStr = "Филе курицы кладём на доску, накрываем плёнкой и отбиваем скалкой так, чтобы получился пласт мяса в 1 см. Обсыпаем солью и перцем с двух сторон. Обжариваем на сковороде-гриль по 3 минуты с каждой стороны. Смазываем нижюю часть булочки йогуртом.";
        String recipeSoupStr = "Пока варится бульон, пожарим овощи. Вымойте и почистите свёклу, морковь и лук. Свёклу натрите на крупной тёрке, а морковь — на средней. Лук нарежьте кубиками.\n";
        String recipeCheeseSoupStr = "В сотейнике на оливковом масле обжарить нарезанный репчатый лук, морковь, натертую на крупной терке." +
                "Дать закипеть воде в кастрюле.\n" +
                "Добавить в кастрюлю нарезанный кубиками картофель, варить до полуготовности.";
        String recipeSushiStr = "Пока рис варится займемся заправкой, смешаем сахар, соль и уксус и хорошенько размешаем. Разрезаем рыбу на полоски толщиной 1,5 см. ";
        //"Также очищаем авокадо и нарезаем ломтиками. (P.S При выборе авокадо старайтесь купить мягкий, спелый, поскольку если авокадо попадется не дозревший, роллы в готовом виду будут горчить и испортят весь вкус.)";
        String recipeHawaiianPizzaStr = "Начнем с американского теста. " +
                "Прямо в муку добавьте быстрые дрожжи, пару щепоток соли и 2 чайные ложки сахара, перемешайте." +
                "Влейте 3 столовые ложки оливкового масла и стакан воды. " +
                "Дождитесь, чтобы тесто поднялось и раскатайте его тонким слоем.";
        String recipeUdon = "В большую кастрюлю с разогретым растительным маслом всыпать лук" +
                " и чеснок и, помешивая, обжарить до золотистого оттенка.";
        String recipeCake = "1. Отделите белки от желтков, взбейте белки миксером до устойчивых пиков, добавив в конце взбивания 75 г сахара. 2. Взбейте желтки с 75 г сахара до светлой однородной массы.";
        cuisineRepository.save(new Cuisine("Американская"));
        cuisineRepository.save(new Cuisine("Русская"));
        cuisineRepository.save(new Cuisine("Китайская"));
        cuisineRepository.save(new Cuisine("Японская"));
        cuisineRepository.save(new Cuisine("Итальянская"));

        measureRepository.save(new Measure("кг."));
        Measure kg = measureRepository.findByName("кг.").get(0);
        measureRepository.save(new Measure("г."));
        Measure g = measureRepository.findByName("г.").get(0);
        measureRepository.save(new Measure("л."));
        Measure l = measureRepository.findByName("л.").get(0);
        measureRepository.save(new Measure("стакан"));
        Measure glass = measureRepository.findByName("стакан").get(0);
        measureRepository.save(new Measure("ст. ложка"));
        measureRepository.save(new Measure("ч. ложка"));
        measureRepository.save(new Measure("мл."));
        measureRepository.save(new Measure("штука"));

        ingredientRepository.save(new Ingredient("томат", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("ананас", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("сахар", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("молоко", measureRepository.findByName("стакан").get(0)));
        ingredientRepository.save(new Ingredient("сода", measureRepository.findByName("ч. ложка").get(0)));

        ingredientRepository.save(new Ingredient("соль", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("бекон", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("спагетти", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("соль", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("курица", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("кетчуп", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("свекла", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("картофель", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("cыр", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("масло", measureRepository.findByName("мл.").get(0)));
        ingredientRepository.save(new Ingredient("рис", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("рыба", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("авокадо", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("красный перец", measureRepository.findByName("штука").get(0)));
        ingredientRepository.save(new Ingredient("шоколад", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("мука", measureRepository.findByName("г.").get(0)));
        ingredientRepository.save(new Ingredient("сливки", measureRepository.findByName("мл.").get(0)));


        //pizza
        recipeRepository.save(new Recipe(namePizzaRecipe, recipePizzaStr, "http://188.166.30.145/topfoodrecipes/pizza.jpg", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe pizzaRecipe = recipeRepository.findByName(namePizzaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("молоко").get(0), pizzaRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), pizzaRecipe, 1));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("томат").get(0), pizzaRecipe, 1));
        //pasta
        recipeRepository.save(new Recipe(namePastaRecipe, recipePastaStr, "http://188.166.30.145/topfoodrecipes/pasta.jpg", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe pastaRecipe = recipeRepository.findByName(namePastaRecipe).get(0);

        /*>>>>>>>>>>>>>>>add picture>>>>>>>>>>>>>>>*/
        /*-----------convert File to MultipartFile-----------*/
        /*
        Path path = Paths.get("/root/files/main.jpg");
        String name = "main.jpg";
        String originalFileName = "main.jpg";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        try {
            recipeService.storeFile(result, pastaRecipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pastaRecipe.setImage(String.format("%s%s_%s", imagePrefix, pastaRecipe.getName(), result.getOriginalFilename()));
        */
        /*<<<<<<<<<<<<<<<<add picture<<<<<<<<<<<<<<<<<*/

        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("спагетти").get(0), pastaRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("бекон").get(0), pastaRecipe, 3));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), pastaRecipe, 1));
        //burger
        recipeRepository.save(new Recipe(nameBurgerRecipe, recipeBurgerStr, "http://188.166.30.145/topfoodrecipes/burger.jpg", adminUser, cuisineRepository.findByName("Американская").get(0)));
        Recipe burgerRecipe = recipeRepository.findByName(namePastaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), burgerRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("бекон").get(0), burgerRecipe, 3));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("кетчуп").get(0), burgerRecipe, 1));
        //soup
        recipeRepository.save(new Recipe(nameSoupRecipe, recipeSoupStr, "http://188.166.30.145/topfoodrecipes/soup.jpg", adminUser, cuisineRepository.findByName("Русская").get(0)));
        Recipe soupRecipe = recipeRepository.findByName(namePastaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("картофель").get(0), soupRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("свекла").get(0), soupRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), soupRecipe, 5));
        //сheese soup
        recipeRepository.save(new Recipe(nameCheeseSoupRecipe, recipeCheeseSoupStr, "http://188.166.30.145/topfoodrecipes/cheeseSoup.jpg", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe cheeseSouprecipe = recipeRepository.findByName(nameCheeseSoupRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), cheeseSouprecipe, 10));
//        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сыр").get(0),cheeseSouprecipe,150));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("картофель").get(0), cheeseSouprecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("масло").get(0), cheeseSouprecipe, 20));
        //sushi
        recipeRepository.save(new Recipe(nameSushiCalifornia, recipeSushiStr, "http://188.166.30.145/topfoodrecipes/sushi.jpg", adminUser, cuisineRepository.findByName("Японская").get(0)));
        Recipe sushiRecipe = recipeRepository.findByName(nameSushiCalifornia).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), sushiRecipe, 15));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рыба").get(0), sushiRecipe, 200));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рис").get(0), sushiRecipe, 150));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("авокадо").get(0), sushiRecipe, 1));
        //pizza
        recipeRepository.save(new Recipe(nameHawaiianPizza, recipeHawaiianPizzaStr, "http://188.166.30.145/topfoodrecipes/pizzaHaw.jpg", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe pizzaHRecipe = recipeRepository.findByName(nameHawaiianPizza).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), pizzaHRecipe, 10));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("курица").get(0), pizzaHRecipe, 200));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("ананас").get(0), pizzaHRecipe, 1));
        //udon
        recipeRepository.save(new Recipe(nameUdon, recipeUdon, "http://188.166.30.145/topfoodrecipes/udon.jpg", adminUser, cuisineRepository.findByName("Китайская").get(0)));
        Recipe udonRecipe = recipeRepository.findByName(nameUdon).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("спагетти").get(0), udonRecipe, 120));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), udonRecipe, 20));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("курица").get(0), udonRecipe, 120));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("красный перец").get(0), udonRecipe, 1));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("авокадо").get(0), udonRecipe, 1));
        //cake
        recipeRepository.save(new Recipe(nameCake, recipeCake, "http://188.166.30.145/topfoodrecipes/cake.jpg", adminUser, cuisineRepository.findByName("Русская").get(0)));
        Recipe cakeRecipe = recipeRepository.findByName(nameCake).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), cakeRecipe, 100));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("шоколад").get(0), cakeRecipe, 140));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мука").get(0), cakeRecipe, 100));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливки").get(0), cakeRecipe, 40));

    }
}
