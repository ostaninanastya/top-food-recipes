package com.topfood.recipes.measure.repository;

import com.topfood.recipes.measure.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasureRepository extends JpaRepository<Measure, Long> {
    List<Measure> findByName(String name);
}
