package com.topfood.recipes.like.repository;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.like.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
