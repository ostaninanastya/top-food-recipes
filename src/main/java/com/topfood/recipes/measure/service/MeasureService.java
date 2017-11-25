package com.topfood.recipes.measure.service;

import com.topfood.recipes.common.Enums.ErrorCodes;
import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.cuisine.repository.CuisineRepository;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.measure.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.topfood.recipes.common.Enums.ErrorCodes.OK;

@Service
public class MeasureService {

    @Autowired
    private MeasureRepository measureRepository;

    public List<Measure> findAll() {
        return measureRepository.findAll();
    }

    public Measure getByID(String measure_id)
    {
        return measureRepository.findOne(Long.valueOf(measure_id));
    }

    public ErrorCodes add(Measure measure){
        measureRepository.save(measure);
        return (OK);
    }
    public void delete(String measure_id){
        measureRepository.delete(Long.valueOf(measure_id));
    }

    public void update(Measure newMeasure)
    {
        Measure measure = measureRepository.findOne(newMeasure.getMeasure_id());
        measure.setName(newMeasure.getName());
        measureRepository.flush();
    }

}
