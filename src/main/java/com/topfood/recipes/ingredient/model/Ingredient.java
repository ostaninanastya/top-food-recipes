package com.topfood.recipes.ingredient.model;

import com.topfood.recipes.cuisine.model.Cuisine;
import com.topfood.recipes.measure.model.Measure;
import com.topfood.recipes.user.model.User;

import javax.persistence.*;

@Entity
@Table(name="ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="ingredient_id")
    private Long ingredient_id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "measure_id")
    private Measure measure;

    public Ingredient() {
    }

    public Ingredient(String ingredient_name, Measure measure) {
        this.name = ingredient_name;
        this.measure = measure;
    }


    public Long getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Long ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String ingredient_name) {
        this.name = ingredient_name;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
