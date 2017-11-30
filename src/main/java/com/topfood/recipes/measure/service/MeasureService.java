package com.topfood.recipes.measure.service;

import com.topfood.recipes.common.enums.ErrorCodes;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.measure.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;

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
