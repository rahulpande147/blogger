package com.blogs.blogger.controllers;

import com.blogs.blogger.models.Blog;
import com.blogs.blogger.models.User;
import com.blogs.blogger.services.BlogService;
import com.blogs.blogger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    //Get blog by UserID
    @GetMapping("/user/{userId}/blog")
    public Optional<Blog> getBlogByUserId (@PathVariable Long userId){
        return blogService.getBlogByUserId(userId);
    }

    //Create blog by UserID
    @PostMapping("/user/{userId}/blog")
    public Blog addBlog(@PathVariable Long userId, @RequestBody Blog blog){
        return blogService.addBlog(userId, blog);
    }

    //Update blog detail by UserID
    @PutMapping("/user/{userId}/blog/{blogId}")
    public Blog updateBlog (@PathVariable Long userId,
                            @PathVariable Long blogId, @RequestBody Blog blog){
        return blogService.updateBlog(userId, blogId, blog);
    }

    //Delete blog by UserID
    @DeleteMapping("/user/{userId}/blog/{blogId}")
    public String delete (@PathVariable Long userId, @PathVariable Long blogId){

        return blogService.delete(userId, blogId);
    }
}
