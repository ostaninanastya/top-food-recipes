package com.topfood.recipes.cuisine.service;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;

@Service
public class CuisineService {
    @Autowired
    private CuisineRepository cuisineRepository;


    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    public Cuisine getByID(String cuisine_id)
    {
        return cuisineRepository.findOne(Long.valueOf(cuisine_id));
    }

    public ErrorCodes add(Cuisine cuisine){
        cuisineRepository.save(cuisine);
        return (OK);
    }
    public void delete(String cuisine_id){
        cuisineRepository.delete(Long.valueOf(cuisine_id));
    }

    public void update(Cuisine newCuisine)
    {
        Cuisine cuisine = cuisineRepository.findOne(newCuisine.getCuisine_id());
        cuisine.setName(newCuisine.getName());
        cuisineRepository.flush();
    }

}
