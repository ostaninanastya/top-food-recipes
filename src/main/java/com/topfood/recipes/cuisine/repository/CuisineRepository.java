package com.topfood.recipes.cuisine.repository;

import com.topfood.recipes.cuisine.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

}
