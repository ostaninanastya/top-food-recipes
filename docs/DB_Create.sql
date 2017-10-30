CREATE TABLE `User` (
	`user_id` bigint NOT NULL AUTO_INCREMENT,
	`user_name` varchar NOT NULL,
	`user_password` varchar NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `Recipe` (
	`recipe_id` bigint NOT NULL AUTO_INCREMENT,
	`recipe` TEXT NOT NULL,
	`recipe_image` mediumblob NOT NULL,
	`cuisine_id` bigint NOT NULL,
	`user_id` bigint NOT NULL,
	PRIMARY KEY (`recipe_id`)
);

CREATE TABLE `Ingredient` (
	`ingredient_name` varchar NOT NULL,
	`ingredient_id` bigint NOT NULL AUTO_INCREMENT,
	`measure_id` bigint NOT NULL,
	PRIMARY KEY (`ingredient_id`)
);

CREATE TABLE `Ingredients_of_recipe` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`recipe_id` bigint NOT NULL,
	`ingredient_id` bigint NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Measure` (
	`measure_id` bigint NOT NULL AUTO_INCREMENT,
	`measure_name` varchar NOT NULL,
	PRIMARY KEY (`measure_id`)
);

CREATE TABLE `Сuisine` (
	`cuisine_id` bigint NOT NULL AUTO_INCREMENT,
	`cuisine_name` varchar NOT NULL,
	PRIMARY KEY (`cuisine_id`)
);

ALTER TABLE `Recipe` ADD CONSTRAINT `Recipe_fk0` FOREIGN KEY (`cuisine_id`) REFERENCES `Сuisine`(`cuisine_id`);

ALTER TABLE `Recipe` ADD CONSTRAINT `Recipe_fk1` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Ingredient` ADD CONSTRAINT `Ingredient_fk0` FOREIGN KEY (`measure_id`) REFERENCES `Measure`(`measure_id`);

ALTER TABLE `Ingredients_of_recipe` ADD CONSTRAINT `Ingredients_of_recipe_fk0` FOREIGN KEY (`recipe_id`) REFERENCES `Recipe`(`recipe_id`);

ALTER TABLE `Ingredients_of_recipe` ADD CONSTRAINT `Ingredients_of_recipe_fk1` FOREIGN KEY (`ingredient_id`) REFERENCES `Ingredient`(`ingredient_id`);

