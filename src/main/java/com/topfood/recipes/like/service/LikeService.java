package com.topfood.recipes.like.service;

import com.topfood.recipes.common.Enums.ErrorCodes;
import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.like.model.Like;
import com.topfood.recipes.like.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.topfood.recipes.common.Enums.ErrorCodes.OK;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;


    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public Like getByID(String like_id)
    {
        return likeRepository.findOne(Long.valueOf(like_id));
    }

    public ErrorCodes add(Like like){
        likeRepository.save(like);
        return (OK);
    }
    public void delete(String like_id){
        likeRepository.delete(Long.valueOf(like_id));
    }

    public void update(Like newLike)
    {
        Like like = likeRepository.findOne(newLike.getId());
        like.setRecipe(newLike.getRecipe());
        like.setSign(newLike.getSign());
        like.setTimestamp(newLike.getTimestamp());
        like.setUser(newLike.getUser());
        likeRepository.flush();
    }

}
