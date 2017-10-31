package com.topfood.recipes.cuisine.service;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CuisineService {
    @Autowired
    private CuisineRepository cuisineRepository;

    @PostConstruct
    public void init(){
        cuisineRepository.save(new Cuisine("Американская"));
        cuisineRepository.save(new Cuisine("Русская"));
        cuisineRepository.save(new Cuisine("Китайская"));
        cuisineRepository.save(new Cuisine("Японская"));
        cuisineRepository.save(new Cuisine("Итальянская"));
    }

    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    public Cuisine getByID(String cuisine_id)
    {
        return cuisineRepository.findOne(Long.valueOf(cuisine_id));
    }

    public void add(Cuisine cuisine){
        cuisineRepository.save(cuisine);
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
