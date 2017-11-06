package com.topfood.recipes.measure.model;

import javax.persistence.*;

@Entity
@Table(name = "measure")
    public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "measure_id")
    private Long measure_id;

    @Column(name = "name")
    private String name;

    public Long getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(Long measure_id) {
        this.measure_id = measure_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Measure() {
    }

    public Measure (String name) {
        this.name = name;
    }
}
