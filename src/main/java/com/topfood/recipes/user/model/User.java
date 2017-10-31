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
    private String user_name;
    @Column(name="user_password")
    private String user_password;

    public User(String user_name, String user_password) {
        this.user_name = user_name;
        this.user_password = user_password;
    }
    public User(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


}
