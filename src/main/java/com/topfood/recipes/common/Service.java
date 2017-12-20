package com.topfood.recipes.common;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
import com.topfood.recipes.recipe.service.RecipeService;
import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.repository.UserRepository;

@org.springframework.stereotype.Service
public class Service {

    private static final Logger LOG = LoggerFactory.getLogger(Service.class);
    
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
    @Value("${upload.folder}")
    private String uploadFolder;

    @PostConstruct
    public void init() {
        try {
            String filename;
            File file;
            userRepository.save(new User("admin", "admin"));
            User adminUser = userRepository.findByName("admin").get(0);
            String namePizzaRecipe = "Пицца маргарита";
            String namePastaRecipe = "Паста карбонара с беконом";
            String nameBurgerRecipe = "Бургер с курицей";
            String nameSoupRecipe = "Борщ";
            String nameCheeseSoupRecipe = "Сырный суп";
            String nameSushiCalifornia = "Красная рыба в пивном соусе";
            String nameHawaiianPizza = "Пицца Гавайская";
            String nameUdon = "Удон с курицей";
            String nameCake = "Торт Прага";
            String nameSirnik = "Сырники из творога";
            String nameVarenik = "Ленивые вареники";
            String nameKasha = "Рисовая каша";
            String nameSous = "Кисло-сладкий соус";
            String nameOgurec = "Огурцы в остром маринаде";
            String nameKapusta = "Капуста с анчоусами";
            String nameSltimbokka = "Сальтимбокка";
            String nameNioki = "Ньокки";
            String nameYaicSalat = "Яичный салат";
            String nameBrownie = "Брауни";
            String nameDango = "Данго";
            String nameOmlet = "Японский омлет";
            String nameMiso = "Луковый мисо суп";
    
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

            String recipeSirnik = "Положите весь творог в кастрюльку и разомните его вилкой так, " +
                    "чтобы в нем не осталось крупных комков. Разбейте в него яйца, всыпьте сахар " +
                    "и тщательно все перемешайте. Лучше не использовать слишком сухой или слишком " +
                    "влажный творог, иначе сырники будут разваливаться в процессе приготовления. " +
                    "Всыпьте в творог 5 столовых ложек (с горкой) муки и тщательно перемешайте. " +
                    "Можно добавить немного больше муки, сырники получатся тогда более плотными. " +
                    "Или муки можно добавить чуть меньше, и тогда сырники будут нежнее. В итоге у вас " +
                    "должна получиться однородная масса, из которой можно будет лепить сырники. " +
                    "Поставьте сковороду на средний огонь и налейте в нее подсолнечное масло. Насыпьте " +
                    "на тарелку немного муки. Слепите несколько небольших шариков из получившейся творожной " +
                    "массы и положите их на тарелку. Лучше лепить разом 4–5 шариков — столько, сколько " +
                    "поместится одновременно на сковороду. Затем по очереди обкатывайте творожные шарики в " +
                    "муке, плющите их в небольшие лепешки (они не должны быть слишком тонкие) и выкладывайте " +
                    "на сковороду. Обжаривайте сырники 1–2 минуты до появления золотистой корочки. Затем переверните " +
                    "их на другую сторону и также обжарьте до золотистого состояния. Повторяйте, пока творог не закончится.";

            String recipeVarenik = "Воду (примерно 1 литр) поставить на большой огонь, посолить по " +
                    "вкусу — хватает большой щепотки. В творог разбить яйцо, добавить сахар и манку " +
                    "и тщательно все перемешать. Рабочую поверхность присыпать мукой, обвалять в ней " +
                    "половину творожной смеси, скатать в колбаску диаметром около двух сантиметров и " +
                    "нарезать ромбиками шириной примерно полтора сантиметра. Такой размер — буквально " +
                    "на один укус, любители могут нарезать и крупнее. Готовые вареники опустить в кипящую " +
                    "воду и стоять наготове с шумовкой: как только вареники начнут всплывать, их нужно " +
                    "вынимать и складывать в подготовленную посуду со сливочным маслом на дне. Когда " +
                    "все вареники будут готовы, тщательно их перемешать и выложить в глубокую тарелку. " +
                    "Подавать со сметаной или фруктами.";

            String recipeKasha = "В сотейнике смешать сливки с молоком и цедрой, снятой с половины " +
                    "апельсина. Стручок ванили разрезать вдоль, соскрести ножом внутренности и " +
                    "вместе со шкуркой бросить их в молоко со сливками. Довести до кипения, засыпать " +
                    "рис, уменьшить огонь и, периодически помешивая, варить до готовности (на это уйдет " +
                    "пятнадцать-восемнадцать минут). Помешивать обязательно, потому что рис иначе станет " +
                    "опускаться на дно и пригорать. Как только рис будет готов — вмешать сгущенное молоко, " +
                    "вытащить ваниль и снять с огня. Дать каше чуть остыть, положить кусочек сливочного масла, " +
                    "чуть перемешать и подавать. Сверху можно выложить дольки апельсина, очищенные от шкурки и пленок.";

            String recipeSous = "Сахар, уксус, томатное пюре, соевый соус и апельсиновый сок отправьте в ковшик. " +
                    "Муку смешайте с 4 столовыми ложками воды и тоже добавьте в ковш. а медленном огне доведите " +
                    "соус до кипения, постоянно помешивая. Подавайте горячим — этот соус подходит к жареной свинине, " +
                    "отбивным и так далее.";

            String recipeOgurec = "Огурцы очистить от кожуры и нарезать одинаковыми брусочками. " +
                    "Брусочки не должны быть длиннее 6–7 сантиметров, иначе их будет неудобно " +
                    "захватывать палочками. Кунжутное масло смешать с черным рисовым уксусом и соевым " +
                    "соусом. Перец чили нарезать тонкими кольцами и добавить в маринад из уксуса, масла и " +
                    "соевого соуса. Залить нарезанные брусками огурцы готовым маринадом и дать настояться 2–3 " +
                    "минуты. После чего подавать в качестве острой закуски или гарнира к жареной или отварной говядине.";

            String recipeKapusta = "Капусту аккуратно нашинковать — чем тоньше, тем лучше. На сковородке " +
                    "разогреть столовую ложку оливкового масла. Раздавить прессом для чеснока два чесночных " +
                    "зубчика — прямо в сковородку. Туда же бросить анчоусы. Держать на огне, помешивая, пока " +
                    "анчоусы не превратятся в соус. Добавить к анчоусам нашинкованную капусту, бодро перемешать, " +
                    "подержать минуту и переложить в тарелку.";

            String recipeSltimbokka = "Разогреть духовку до 250 градусов. Отбить куски вырезки до толщины примерно " +
                    "в сантиметр. Посолить, поперчить, положить на каждый несколько листиков шалфея. Обернуть каждую " +
                    "отбивную ломтиками прошутто. Если куски получились довольно большими, можно разрезать их для " +
                    "удобства пополам. В глубокой сковороде с толстым дном растопить 3 столовые ложки сливочного " +
                    "масла — пока оно не начнет пузыриться. Обжарить мясо, выкладывая его шалфеем вниз, до золотистой " +
                    "корочки. Аккуратно перевернуть, готовить еще минуту. Отправить мясо в духовку на пять минут. Тем " +
                    "временем плеснуть в сковороду, где жарилась вырезка, вино. Выпаривать его на сильном огне до тех " +
                    "пор, пока количество вина не уменьшится на треть. Подержать еще немного, интенсивно помешивая, " +
                    "затянуть соус 1 столовой ложкой сливочного масла. Достать мясо из духовки, дать ему отдохнуть " +
                    "несколько минут. Подавать, полив соусом.";

            String recipeNioki = "Очищенный картофель варить в подсоленной воде около 15 минут. Картофель должен " +
                    "стать мягче, но сохранять форму. Размять при помощи вилки или картофельного пресса. Смешать " +
                    "пюре с яйцом и мукой, вымесить тесто. Сформировать из теста тонкие жгуты и выложить на " +
                    "подпыленный мукой стол. Нарезать тесто кусочками длиной около сантиметра. В большой кастрюле " +
                    "довести подсоленную воду до кипения и бросить туда ньокки. Варить 3-5 минут или до того момента, " +
                    "когда ньокки всплывут. Слить воду и подавать, посыпав тертым пармезаном и украсив веточкой базилика.";

            String recipeYaicSalat = "Яйца сварить вкрутую, охладить, почистить, мелко нарубить. Мелко нарезать лук " +
                    "(только зеленую часть). Соединить лук и яйца, добавить майонез (можно приготовить самостоятельно), " +
                    "соль, перец, тщательно перемешать.";

            String recipeBrownie = "Шоколад разломать на кусочки и вместе со сливочным маслом растопить на водяной " +
                    "бане, не переставая все время помешивать лопаткой или деревянной ложкой. Получившийся густой " +
                    "шоколадный соус снять с водяной бани и оставить остывать. Тем временем смешать яйца со ста " +
                    "граммами коричневого сахара: яйца разбить в отдельную миску и взбить, постепенно добавляя сахар. " +
                    "Взбивать можно при помощи миксера или вручную — как больше нравится, — но не меньше двух с " +
                    "половиной-трех минут. Острым ножом на разделочной доске порубить грецкие орехи. Предварительно " +
                    "их можно поджарить на сухой сковороде до появления аромата, но это необязательная опция. В " +
                    "остывший растопленный со сливочным маслом шоколад аккуратно добавить оставшийся сахар, затем " +
                    "муку и измельченные орехи и все хорошо перемешать венчиком. Затем влить сахарно-яичную смесь и " +
                    "тщательно смешать с шоколадной массой. Цвет у теста должен получиться равномерным, без разводов. " +
                    "Разогреть духовку до 200 градусов. Дно небольшой глубокой огнеупорной формы выстелить листом " +
                    "бумаги для выпечки или калькой. Перелить тесто в форму. Поставить в духовку и выпекать двадцать " +
                    "пять — тридцать минут до появления сахарной корочки." +
                    "Термометр для духовки Готовый пирог вытащить из духовки, дать остыть и нарезать на квадратики " +
                    "острым ножом или ножом для пиццы — так кусочки получатся особенно ровными. Подавать брауни " +
                    "можно просто так, а можно посыпать сверху сахарной пудрой или разложить квадратики по тарелкам " +
                    "и украсить каждую порцию шариком ванильного мороженого.";

            String recipeDango = "Подогреть 200 мл воды, но не кипятить. Добавить в воду рисовую муку, перемешать. " +
                    "Из данного теста сформировать небольшие шарики (размером с грецкий орех). Шарики варить на пару " +
                    "15 минут. Делаем соус. Смешать соевый соус, 200 мл воды, сахар, крахмал. Эту смесь варить до " +
                    "загустения, постоянно помешивая, но не кипятить. Приготовленные шарики надеть на деревянные " +
                    "шампуры, потом полить соусом. Можно подавать к столу.";

            String recipeOmlet = "Яйца смешайте с соевым соусом и рисовым уксусом. Если соус соленый, добавьте " +
                    "чуть-чуть сахара (треть чайной ложки), а если сладкий, то можно немного посолить. Слегка " +
                    "взбейте смесь вилкой, чтобы получилась однородная масса. Смажьте сковороду, нагрейте ее " +
                    "на небольшом огне и налейте немного яичной смеси, чтобы выпечь тонкий блинчик. Когда яйца " +
                    "схватятся, загните два противоположных края к центру. Затем сложите блинчик пополам вдоль. " +
                    "Сдвиньте получившийся сверток к самому краю сковороды, а на свободную часть налейте еще " +
                    "порцию яичной смеси. Новый блинчик должен одним краем приклеиться к первому. Когда второй " +
                    "блинчик немного схватится, накрутите его на первый. Повторяйте этот и предыдущий этапы, пока " +
                    "не кончится смесь или рулетик не получится достаточно толстым. Осталось нарезать омлет поперек и подать.";

            String recipeMiso = "Приготовьте бульон даси. Для этого залейте сушеную морскую капусту водой и " +
                    "доведите до кипения. Добавьте пасту мисо, соль и варите 1 минуту. Добавьте тофу, " +
                    "нарезанный кубиками. Варите 2 минуты. Зеленый лук измельчите и добавьте в бульон. Варите 1 минуту.";

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
            measureRepository.save(new Measure("шт."));
            measureRepository.save(new Measure("зубчик"));
            measureRepository.save(new Measure("пучок"));
    
            ingredientRepository.save(new Ingredient("томат", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("ананас", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("сахар", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("молоко", measureRepository.findByName("стакан").get(0)));
            ingredientRepository.save(new Ingredient("сода", measureRepository.findByName("ч. ложка").get(0)));
            ingredientRepository.save(new Ingredient("соль", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("бекон", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("спагетти", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("курица", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("кетчуп", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("свекла", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("картофель", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("cыр", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("масло", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("рис", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("рыба", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("авокадо", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("красный перец", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("шоколад", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("мука", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("сливки", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("творог", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("яйцо", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("подсолнечное масло", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("манная крупа", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("сливочное масло", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("сгущёное молоко", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("ванильный стручок", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("апельсин", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("уксус", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("соевый соус", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("томатное пюре", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("апельсиновый сок", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("кукурузная мука", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("рисовый уксус", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("огурцы", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("кунжутное масло", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("перец чили", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("белокачанная капуста", measureRepository.findByName("шт.").get(0)));
            ingredientRepository.save(new Ingredient("чеснок", measureRepository.findByName("зубчик").get(0)));
            ingredientRepository.save(new Ingredient("анчоусы", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("оливковое масло", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("прошутто", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("телячья вырезка", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("шалфей", measureRepository.findByName("пучок").get(0)));
            ingredientRepository.save(new Ingredient("белое сухое вино", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("пармезан", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("майонез", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("зелёный лук", measureRepository.findByName("пучок").get(0)));
            ingredientRepository.save(new Ingredient("перец чёрный молотый", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("грецкие орехи", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("рисовая мука", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("вода", measureRepository.findByName("мл.").get(0)));
            ingredientRepository.save(new Ingredient("крахмал", measureRepository.findByName("ст. ложка").get(0)));
            ingredientRepository.save(new Ingredient("тофу", measureRepository.findByName("г.").get(0)));
            ingredientRepository.save(new Ingredient("мисо паста", measureRepository.findByName("ст. ложка").get(0)));
            ingredientRepository.save(new Ingredient("морская капуста", measureRepository.findByName("г.").get(0)));


    
            //pizza
            filename = "pizza.jpg";
            recipeRepository.save(new Recipe(namePizzaRecipe, recipePizzaStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe pizzaRecipe = recipeRepository.findByName(namePizzaRecipe).get(0);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("молоко").get(0), pizzaRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), pizzaRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("томат").get(0), pizzaRecipe, 1.0));
            //pasta
            filename = "pasta.jpg";
            recipeRepository.save(new Recipe(namePastaRecipe, recipePastaStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe pastaRecipe = recipeRepository.findByName(namePastaRecipe).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile((File)file, pastaRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("спагетти").get(0), pastaRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("бекон").get(0), pastaRecipe, 3.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), pastaRecipe, 1.0));
            //burger
            filename = "burger.jpg";
            recipeRepository.save(new Recipe(nameBurgerRecipe, recipeBurgerStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Американская").get(0)));
            Recipe burgerRecipe = recipeRepository.findByName(nameBurgerRecipe).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, burgerRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), burgerRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("бекон").get(0), burgerRecipe, 3.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("кетчуп").get(0), burgerRecipe, 1.0));
            //soup
            filename = "soup.jpg";
            recipeRepository.save(new Recipe(nameSoupRecipe, recipeSoupStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Русская").get(0)));
            Recipe soupRecipe = recipeRepository.findByName(nameSoupRecipe).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, soupRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("картофель").get(0), soupRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("свекла").get(0), soupRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), soupRecipe, 5.0));
            //сheese soup
            filename = "cheeseSoup.jpg";
            recipeRepository.save(new Recipe(nameCheeseSoupRecipe, recipeCheeseSoupStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe cheeseSouprecipe = recipeRepository.findByName(nameCheeseSoupRecipe).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, cheeseSouprecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), cheeseSouprecipe, 10.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("картофель").get(0), cheeseSouprecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("масло").get(0), cheeseSouprecipe, 20.0));
            //sushi
            filename = "sushi.jpg";
            recipeRepository.save(new Recipe(nameSushiCalifornia, recipeSushiStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Японская").get(0)));
            Recipe sushiRecipe = recipeRepository.findByName(nameSushiCalifornia).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, sushiRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), sushiRecipe, 15.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рыба").get(0), sushiRecipe, 200.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рис").get(0), sushiRecipe, 150.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("авокадо").get(0), sushiRecipe, 1.0));
            //pizza
            filename = "pizzaHaw.jpg";
            recipeRepository.save(new Recipe(nameHawaiianPizza, recipeHawaiianPizzaStr, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe pizzaHRecipe = recipeRepository.findByName(nameHawaiianPizza).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, pizzaHRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), pizzaHRecipe, 10.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("курица").get(0), pizzaHRecipe, 200.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("ананас").get(0), pizzaHRecipe, 1.0));
            //udon
            filename = "udon.jpg";
            recipeRepository.save(new Recipe(nameUdon, recipeUdon, imagePrefix + filename, adminUser, cuisineRepository.findByName("Китайская").get(0)));
            Recipe udonRecipe = recipeRepository.findByName(nameUdon).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, udonRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("спагетти").get(0), udonRecipe, 120.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), udonRecipe, 20.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("курица").get(0), udonRecipe, 120.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("красный перец").get(0), udonRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("авокадо").get(0), udonRecipe, 1.0));
            //cake
            filename = "cake.jpg";
            recipeRepository.save(new Recipe(nameCake, recipeCake, imagePrefix + filename, adminUser, cuisineRepository.findByName("Русская").get(0)));
            Recipe cakeRecipe = recipeRepository.findByName(nameCake).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, cakeRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), cakeRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("шоколад").get(0), cakeRecipe, 140.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мука").get(0), cakeRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливки").get(0), cakeRecipe, 40.0));

            //pancake
            filename = "pancake.jpg";
            recipeRepository.save(new Recipe(nameSirnik, recipeSirnik, imagePrefix + filename, adminUser, cuisineRepository.findByName("Русская").get(0)));
            Recipe pancakeRecipe = recipeRepository.findByName(nameSirnik).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, pancakeRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("творог").get(0), pancakeRecipe, 500.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("яйцо").get(0), pancakeRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мука").get(0), pancakeRecipe, 180.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), pancakeRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("подсолнечное масло").get(0), pancakeRecipe, 100.0));
            //vareniki
            filename = "varenik.jpg";
            recipeRepository.save(new Recipe(nameVarenik, recipeVarenik, imagePrefix + filename, adminUser, cuisineRepository.findByName("Русская").get(0)));
            Recipe varenikRecipe = recipeRepository.findByName(nameVarenik).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, varenikRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("творог").get(0), varenikRecipe, 500.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("яйцо").get(0), varenikRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("манная крупа").get(0), varenikRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мука").get(0), varenikRecipe, 20.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливочное масло").get(0), varenikRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), varenikRecipe, 30.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), varenikRecipe, 10.0));
            //rice porrige
            filename = "porrige.jpg";
            recipeRepository.save(new Recipe(nameKasha, recipeKasha, imagePrefix + filename, adminUser, cuisineRepository.findByName("Русская").get(0)));
            Recipe porrigeRecipe = recipeRepository.findByName(nameKasha).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, porrigeRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("молоко").get(0), porrigeRecipe, 340.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливки").get(0), porrigeRecipe, 160.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рис").get(0), porrigeRecipe, 60.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливочное масло").get(0), porrigeRecipe, 40.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сгущёное молоко").get(0), porrigeRecipe, 120.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("ванильный стручок").get(0), porrigeRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("апельсин").get(0), porrigeRecipe, 1.0));
            //sous
            filename = "sous.jpg";
            recipeRepository.save(new Recipe(nameSous, recipeSous, imagePrefix + filename, adminUser, cuisineRepository.findByName("Китайская").get(0)));
            Recipe sousRecipe = recipeRepository.findByName(nameSous).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, soupRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), soupRecipe, 40.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("уксус").get(0), soupRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соевый соус").get(0), soupRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("томатное пюре").get(0), soupRecipe, 30.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("апельсиновый сок").get(0), soupRecipe, 80.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("кукурузная мука").get(0), soupRecipe, 10.0));
            //cucumber
            filename = "cucumber.jpg";
            recipeRepository.save(new Recipe(nameOgurec, recipeOgurec, imagePrefix + filename, adminUser, cuisineRepository.findByName("Китайская").get(0)));
            Recipe cucumberRecipe = recipeRepository.findByName(nameOgurec).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, cucumberRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рисовый уксус").get(0), cucumberRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("огурцы").get(0), cucumberRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("кунжутное масло").get(0), cucumberRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соевый соус").get(0), cucumberRecipe, 50.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("перец чили").get(0), cucumberRecipe, 1.0));
            //cabbage
            filename = "cabbage.jpg";
            recipeRepository.save(new Recipe(nameKapusta, recipeKapusta, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe cabbageRecipe = recipeRepository.findByName(nameKapusta).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, cabbageRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("белокачанная капуста").get(0), cabbageRecipe, 0.25));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("чеснок").get(0), cabbageRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("анчоусы").get(0), cabbageRecipe, 60.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("оливковое масло").get(0), cabbageRecipe, 50.0));
            //saltimbokka
            filename = "saltimbokka.jpg";
            recipeRepository.save(new Recipe(nameSltimbokka, recipeSltimbokka, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe saltimbokkaRecipe = recipeRepository.findByName(nameSltimbokka).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, saltimbokkaRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("прошутто").get(0), saltimbokkaRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("телячья вырезка").get(0), saltimbokkaRecipe, 700.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("шалфей").get(0), saltimbokkaRecipe, 3.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливочное масло").get(0), saltimbokkaRecipe, 80.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("белое сухое вино").get(0), saltimbokkaRecipe, 250.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("перец чёрный молотый").get(0), saltimbokkaRecipe, 20.0));
            //nyokki
            filename = "nyokki.jpg";
            recipeRepository.save(new Recipe(nameNioki, recipeNioki, imagePrefix + filename, adminUser, cuisineRepository.findByName("Итальянская").get(0)));
            Recipe nyokkiRecipe = recipeRepository.findByName(nameNioki).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, nyokkiRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("картофель").get(0), nyokkiRecipe, 3.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мука").get(0), nyokkiRecipe, 260.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("яйцо").get(0), nyokkiRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("пармезан").get(0), nyokkiRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), nyokkiRecipe, 50.0));
            //egg salad
            filename = "eggsalad.jpg";
            recipeRepository.save(new Recipe(nameYaicSalat, recipeYaicSalat, imagePrefix + filename, adminUser, cuisineRepository.findByName("Американская").get(0)));
            Recipe  eggsaladRecipe = recipeRepository.findByName(nameYaicSalat).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, eggsaladRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("яйцо").get(0), eggsaladRecipe, 10.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("майонез").get(0), eggsaladRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("зелёный лук").get(0), eggsaladRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("перец чёрный молотый").get(0), eggsaladRecipe, 20.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), eggsaladRecipe, 20.0));
            //brownie
            filename = "brownie.jpg";
            recipeRepository.save(new Recipe(nameBrownie, recipeBrownie, imagePrefix + filename, adminUser, cuisineRepository.findByName("Американская").get(0)));
            Recipe brownieRecipe = recipeRepository.findByName(nameBrownie).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, brownieRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("шоколад").get(0), brownieRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сливочное масло").get(0), brownieRecipe, 180.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), brownieRecipe, 200.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("яйцо").get(0), brownieRecipe, 4.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мука").get(0), brownieRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("грецкие орехи").get(0), brownieRecipe, 100.0));
            //dango
            filename = "dango.jpg";
            recipeRepository.save(new Recipe(nameDango, recipeDango, imagePrefix + filename, adminUser, cuisineRepository.findByName("Японская").get(0)));
            Recipe dangoRecipe = recipeRepository.findByName(nameDango).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, dangoRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рисовая мука").get(0), dangoRecipe, 400.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("вода").get(0), dangoRecipe, 400.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("сахар").get(0), dangoRecipe, 200.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("крахмал").get(0), dangoRecipe, 4.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соевый соус").get(0), dangoRecipe, 100.0));
            //japanese omelette
            filename = "omelette.jpg";
            recipeRepository.save(new Recipe(nameOmlet, recipeOmlet, imagePrefix + filename, adminUser, cuisineRepository.findByName("Японская").get(0)));
            Recipe omeletteRecipe = recipeRepository.findByName(nameOmlet).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, omeletteRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соевый соус").get(0), omeletteRecipe, 15.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("рисовый уксус").get(0), omeletteRecipe, 10.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("яйцо").get(0), omeletteRecipe, 3.0));
            //miso
            filename = "miso.jpg";
            recipeRepository.save(new Recipe(nameMiso, recipeMiso, imagePrefix + filename, adminUser, cuisineRepository.findByName("Японская").get(0)));
            Recipe misoRecipe = recipeRepository.findByName(nameMiso).get(0);
            file = new File(getClass().getClassLoader().getResource("pictures/" + filename).getFile());
            recipeService.storeFile(file, misoRecipe);
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("тофу").get(0), misoRecipe, 300.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("мисо паста").get(0), misoRecipe, 2.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("морская капуста").get(0), misoRecipe, 100.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("зелёный лук").get(0), misoRecipe, 1.0));
            ingredientRecipeRepository.save(new IngredientRecipe(ingredientRepository.findByName("соль").get(0), misoRecipe, 20.0));


        } catch (IOException ioe) {
            LOG.warn("Failed to initialize: " + ioe.getMessage());
        }
    }
}
