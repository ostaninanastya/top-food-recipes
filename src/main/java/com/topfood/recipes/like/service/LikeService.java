package com.topfood.recipes.like.service;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.like.model.Like;
import com.topfood.recipes.like.repository.LikeRepository;
import com.topfood.recipes.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;
import static com.topfood.recipes.common.enums.ErrorCodes.TOO_OFTEN_LIKES;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public List<Like> findByRecipe(Recipe recipe) {

        return likeRepository.findByRecipe(recipe);
    }

    public ErrorCodes add(Like like) {
        if (likeRepository.findByUser(like.getUser()).size() != 0) {
            Like lastLike = likeRepository.findByUser(like.getUser()).get(likeRepository.findByUser(like.getUser()).size() - 1);
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
