package com.topfood.recipes.user.service;

import com.topfood.recipes.common.Enums.ErrorCodes;
import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.topfood.recipes.common.Enums.ErrorCodes.OK;
import static com.topfood.recipes.common.Enums.ErrorCodes.REG_USER_ALREADY_EXISTS;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getByID(String id) {
        return userRepository.findOne(Long.valueOf(id));
    }

    public ErrorCodes add(User user){
        List<User> users = userRepository.findAll();
        for (User u : users)
        {
            if (user.getName().equals(u.getName())){
                return (REG_USER_ALREADY_EXISTS);
            }
        }
        userRepository.save(user);
        return (OK);
    }

    public void delete(String id) {
        userRepository.delete(Long.valueOf(id));
    }

    public void update(User newUser) {
        User user = userRepository.findOne(newUser.getUser_id());
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        userRepository.flush();
    }
}