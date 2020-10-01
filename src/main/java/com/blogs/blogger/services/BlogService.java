package com.blogs.blogger.services;


import com.blogs.blogger.daoimpl.BlogRepository;
import com.blogs.blogger.daoimpl.UserRepository;
import com.blogs.blogger.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    private UserRepository userRepository;

    public Optional<Blog> getBlogByUserId(Long id) {

        if(!blogRepository.existsById(id)) {
            throw new RuntimeException("not found!");
        }

        return blogRepository.findById(id);
    }

    public Blog addBlog(Long userId,
                                    Blog blog) {
        return userRepository.findById(userId)
                .map(user -> {
                    blog.setUser(user);
                    return blogRepository.save(blog);
                }).orElseThrow(() -> new RuntimeException(" not found!"));
    }

    public Blog updateBlog( Long userID,
                                       Long blogId,
                                       Blog blogNew) {

        if(!userRepository.existsById(userID)) {
            throw new RuntimeException(" not found!");
        }

        return blogRepository.findById(blogId)
                .map(blog -> {
                    blog.setBlog_title(blogNew.getBlog_title());
                    blog.setBlog_content(blogNew.getBlog_content());
                    return blogRepository.save(blog);
                }).orElseThrow(() -> new RuntimeException(" not found!"));
    }

    public String delete( Long userId,
                                    Long blogId) {

        if(!userRepository.existsById(userId)) {
            throw new RuntimeException("Student not found!");
        }

        return blogRepository.findById(blogId)
                .map(blog -> {
                    blogRepository.delete(blog);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new RuntimeException("Contact not found!"));
    }

}

