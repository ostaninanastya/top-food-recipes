package com.topfood.recipes.like.service;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.like.model.Like;
import com.topfood.recipes.like.repository.LikeRepository;
import com.topfood.recipes.recipe.model.Recipe;
import com.topfood.recipes.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;
import static com.topfood.recipes.common.enums.ErrorCodes.TOO_OFTEN_LIKES;
import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.repository.UserRepository;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Like> findByRecipe(Recipe recipe) {

        return likeRepository.findByRecipe(recipe);
    }

    public Integer Rating(String id){
        Integer rate = 0;
        for (Like loice: findByRecipe(recipeRepository.findOne(Long.valueOf(id))))
        {
            if (loice.getSign())
                rate++;
            else
                rate--;
        }
        return rate;
    }

    public ErrorCodes add(Like like) {
        User likeUser = like.getUser();

        if (likeUser != null) {
            like.setUser(userRepository.findByName(likeUser.getName()).get(0));
        }

        if (likeRepository.findByUserAndRecipeOrderByTimestampDesc(like.getUser(), like.getRecipe()).size() != 0) {

            Like lastLike = likeRepository.findByUserAndRecipeOrderByTimestampDesc(like.getUser(), like.getRecipe()).get(0);
            //getTime returns time in milliseconds. we divide into 1000 to get seconds
            if ((like.getTimestamp().getTime() - lastLike.getTimestamp().getTime()) / 1000 > 120) {
                likeRepository.save(like);
                return (OK);
            } else return (TOO_OFTEN_LIKES);
        } else {
            likeRepository.save(like);
            return (OK);
        }

    }

}
