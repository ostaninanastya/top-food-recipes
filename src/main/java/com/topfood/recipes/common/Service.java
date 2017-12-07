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
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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
    @PostConstruct
    public void init()
    {
        userRepository.save(new User("admin", "admin"));
        User adminUser = userRepository.findByName("admin").get(0);
        String namePizzaRecipe = "Пицца маргарита";
        String namePastaRecipe = "Паста карбонара с беконом";
        String nameBurgerRecipe = "Бургер с курицей";
        String nameSoupRecipe = "Борщ";

        String recipePizzaStr = "Хорошее тесто для пиццы можно приготовить, смешав четыре стакана муки с двумя упаковками сухих дрожжей, четвертью стакана оливкового масла, двумя столовыми ложками сахара, двумя чайными ложки крупной соли и добавив примерно полтора стакана теплой воды.";
        String recipePastaStr = "Разогреваем на сковороде оливковое масло, обжариваем измельченный чеснок. Добавляем нарезанную кубиками ветчину и жарим 3 минуты.\n" +
                "Взбиваем сливки с желтками, добавляем пармезан, соль и перец по вкусу.\n" +
                "Варим спагетти. Кидаем их в сковороду с ветчиной.";
        String recipeBurgerStr = "Филе курицы кладём на доску, накрываем плёнкой и отбиваем скалкой так, чтобы получился пласт мяса в 1 см. Обсыпаем солью и перцем с двух сторон. Обжариваем на сковороде-гриль по 3 минуты с каждой стороны. Смазываем нижюю часть булочки йогуртом.";
        String recipeSoupStr = "Пока варится бульон, пожарим овощи. Вымойте и почистите свёклу, морковь и лук. Свёклу натрите на крупной тёрке, а морковь — на средней. Лук нарежьте кубиками.\n";


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


        //pizza
        recipeRepository.save(new Recipe(namePizzaRecipe, recipePizzaStr,"image", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe pizzaRecipe = recipeRepository.findByName(namePizzaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("молоко").get(0), pizzaRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), pizzaRecipe, 1));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("томат").get(0), pizzaRecipe , 1));
        //pasta
        recipeRepository.save(new Recipe(namePastaRecipe, recipePastaStr,"image", adminUser, cuisineRepository.findByName("Итальянская").get(0)));
        Recipe pastaRecipe = recipeRepository.findByName(namePastaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("спагетти").get(0), pastaRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("бекон").get(0), pastaRecipe, 3));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), pastaRecipe , 1));
        //burger
        recipeRepository.save(new Recipe(nameBurgerRecipe, recipeBurgerStr,"image", adminUser, cuisineRepository.findByName("Американская").get(0)));
        Recipe burgerRecipe = recipeRepository.findByName(namePastaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), burgerRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("бекон").get(0), burgerRecipe, 3));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("кетчуп").get(0), burgerRecipe , 1));
        //soup
        recipeRepository.save(new Recipe(nameSoupRecipe, recipeSoupStr,"image", adminUser, cuisineRepository.findByName("Русская").get(0)));
        Recipe soupRecipe = recipeRepository.findByName(namePastaRecipe).get(0);
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("картофель").get(0), soupRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("свекла").get(0), soupRecipe, 2));
        ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), soupRecipe , 5));

    }
}
