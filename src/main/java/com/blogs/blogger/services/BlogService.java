package com.blogs.blogger.services;


import com.blogs.blogger.NotFoundException;
import com.blogs.blogger.daoimpl.BlogRepository;
import com.blogs.blogger.daoimpl.UserRepository;
import com.blogs.blogger.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    //Getting blog by user id
    public Optional<Blog> getBlogByUserId(Long id) {
        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User not found!");
        }

        return blogRepository.findByUser_id(id);
    }

    //Add blog to user id
    public Blog addBlog(Long userId, Blog blog) {
        return userRepository.findById(userId)
                .map(user -> {
                    blog.setUser(user);
                    return blogRepository.save(blog);
                }).orElseThrow(() -> new NotFoundException("Student not found!"));
    }

    /*public Blog addBlog(Long userId, Blog blog) {
        return userRepository.findById(userId)
                .map(user -> {
                    blog.setUser(user);
                    return blogRepository.save(blog);
                }).orElseThrow(() -> new NotFoundException(" not found!"));
    }*/

    //Update blog detail
    public Blog updateBlog( Long userID,
                                       Long blogId,
                                       Blog blogNew) {

        if(!userRepository.existsById(userID)) {
            throw new NotFoundException(" not found!");
        }

        return blogRepository.findById(blogId)
                .map(blog -> {
                    blog.setBlog_title(blogNew.getBlog_title());
                    blog.setBlog_content(blogNew.getBlog_content());
                    return blogRepository.save(blog);
                }).orElseThrow(() -> new NotFoundException(" not found!"));
    }

    //Delete blog
    public String delete( Long userId, Long blogId) {

        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("UserId not found!");
        }
        return blogRepository.findById(blogId)
                .map(blog -> {
                    blogRepository.delete(blog);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Blogid not found!"));
    }

}

