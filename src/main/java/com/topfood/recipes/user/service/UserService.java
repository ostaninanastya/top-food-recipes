package com.topfood.recipes.user.service;

import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(new User("admin", "admin"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getByID(String id) {
        return userRepository.findOne(Long.valueOf(id));
    }

    public void add(User laptop) {
        userRepository.save(laptop);
    }

    public void delete(String id) {
        userRepository.delete(Long.valueOf(id));
    }

    public void update(User newUser) {
        User user = userRepository.findOne(newUser.getUser_id());
        user.setUser_name(newUser.getUser_name());
        user.setUser_password(newUser.getUser_password());
        userRepository.flush();
    }
}