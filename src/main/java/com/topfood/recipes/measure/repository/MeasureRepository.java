package com.topfood.recipes.measure.repository;

import com.topfood.recipes.measure.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure, Long> {

}
