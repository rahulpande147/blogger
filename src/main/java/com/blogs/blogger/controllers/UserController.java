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

    //Get All Users
    @GetMapping("/user")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    //Get user by ID
    @GetMapping("/user/{id}")
    public User getUsreById(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    //Paged result of all users
    @GetMapping("/user/{pageNo}/{pageSize}/{sortBy}")
    public List<User> getAllUser(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<User> list = userService.getAllUser(pageNo, pageSize, sortBy);
        return list;
    }

    //Search user by name
    @GetMapping("/user/search/{word}/{pageNo}/{pageSize}/{sortBy}")
    public List<User> findByWord (@PathVariable String word,
                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "id") String sortBy){
        List<User> list = userService.getByWord(word, pageNo,pageSize,sortBy);
        return list;
    }

    //Create new user in DB
    @PostMapping("/user")
    public User createUser(@RequestBody  User user){
         return userService.createUser(user);
    }

    //Update user details
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id,
                                  @RequestBody User userNew){
        return userService.updateUser(id, userNew);
    }

    //Delete existing user
    @DeleteMapping("/user/{id}")
    public Optional deleteUser (@PathVariable Long id){
        return userService.deleteUser(id);
    }



    /*@GetMapping("/user/search")
    public Page<User> getByWord (@RequestParam Optional<String> name,
                               @RequestParam Optional<Integer> page,
                               @RequestParam Optional<String> sortBy){
        return userService.getByWord(name, page, sortBy);
    }*/


}
