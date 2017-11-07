package com.topfood.recipes.cuisine.repository;

import com.topfood.recipes.cuisine.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
    List<Cuisine> findByName(String name);
}
