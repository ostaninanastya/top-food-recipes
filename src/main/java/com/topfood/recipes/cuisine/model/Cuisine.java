package com.topfood.recipes.cuisine.model;

import javax.persistence.*;

@Entity
@Table(name="cuisine")
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cuisine_id")
    private Long cuisine_id;

    @Column(name = "name")
    private String name;

    public Long getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(Long cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuisine(){

    }

    public Cuisine(String name){
        this.name = name;
    }
}
