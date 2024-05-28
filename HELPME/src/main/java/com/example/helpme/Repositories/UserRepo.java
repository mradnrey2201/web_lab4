package com.example.helpme.Repositories;

import com.example.helpme.Models.Point;
import com.example.helpme.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

}
