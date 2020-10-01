package com.blogs.blogger.controllers;

import com.blogs.blogger.models.User;
import com.blogs.blogger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public User getUsreById(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody  User user){
         return userService.createUser(user);
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id,
                                  @RequestBody User userNew){
        return userService.updateUser(id, userNew);
    }

    @DeleteMapping("/user/{id}")
    public Optional deleteUser (@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/user/search")
    public Page<User> getByWord (@RequestParam Optional<String> name,
                                 @RequestParam Optional<Integer> page,
                                 @RequestParam Optional<String> sortBy){
        return userService.getByWord(name, page, sortBy);
    }
}
