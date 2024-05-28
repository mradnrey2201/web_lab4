package com.example.helpme.Controllers;

import com.example.helpme.Models.User;
import com.example.helpme.Repositories.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    private String username;
    private String password;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public ResponseEntity<?> main(){
        return ResponseEntity.ok("");
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/email")
    public ResponseEntity<User> clientInfo(@RequestBody User user){
        System.out.println("email: "+ user.getUsername());
        return ResponseEntity.ok(new User("3","vasuka.com"+ user.getUsername()));
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/task")
    public ResponseEntity<User> userInfo(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (userRepo.findByUsername(user.getUsername()) == null) {
            return ResponseEntity.ok(null);
        } else {
            User existingUser = userRepo.findByUsername(user.getUsername());
            if (existingUser == null) {
                return ResponseEntity.ok(null);
            } else {
                if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                    // Пароли совпадают
                    if (principal instanceof UserDetails) {
                        UserDetails userDetails = (UserDetails) principal;
                        Iterable<User> users = userRepo.findAll();
                        System.out.println("email: " + user.getUsername() + user.getPassword());
                        this.username = user.getUsername();
                        this.password = user.getPassword();

                        return ResponseEntity.ok(user);
                    }
                    return ResponseEntity.ok(user);
                } else {
                    // Пароли не совпадают
                    return ResponseEntity.ok(null);
                }
            }
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Iterable<User>> userAdd(@RequestBody User user){
        Iterable<User> users = userRepo.findAll();
        if(userRepo.findByUsername(user.getUsername())!=null){
            return ResponseEntity.ok(null);
        }else {

            String hashedPassword = passwordEncoder.encode(user.getPassword()); // Хэширование пароля
            User us = new User(user.getUsername(), hashedPassword); // Сохранение хэшированного пароля
            userRepo.save(us);


            System.out.println("email: " + user.getUsername() + user.getPassword());
            this.username = user.getUsername();
            this.password = user.getPassword();

            return ResponseEntity.ok(users);

        }

    }






















}
