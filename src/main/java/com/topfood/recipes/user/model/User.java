package com.topfood.recipes.user.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="user_id")
    private Long user_id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public User(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
