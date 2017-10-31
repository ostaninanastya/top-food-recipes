package com.topfood.recipes.user.repository;

import com.topfood.recipes.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

