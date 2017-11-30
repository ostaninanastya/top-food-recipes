package com.topfood.recipes.like.repository;

import com.topfood.recipes.like.model.Like;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user);
    List<Like> findByRecipe(Recipe recipe);
}
