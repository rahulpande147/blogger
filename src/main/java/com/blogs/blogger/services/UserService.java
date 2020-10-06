package com.blogs.blogger.services;


import com.blogs.blogger.NotFoundException;
import com.blogs.blogger.daoimpl.PageRepository;
import com.blogs.blogger.daoimpl.UserRepository;
import com.blogs.blogger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    //Get all user
    public List<User> getAllUser (){
        return userRepository.findAll();
    }

    //Get all paged user
    public List<User> getAllUserPaged(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pagedResult = pageRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
    }

    //Find By Id
    public User getUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }else {
            throw new NotFoundException("Not Found");
        }
    }

    //create new user
    public User createUser(User user){
        return userRepository.save(user);
    }

    //update user
    public User updateUser( Long id, User userNew) {
        return userRepository.findById(id)
                .map(student -> {
                    student.setName(userNew.getName());
                    return userRepository.save(student);
                }).orElseThrow(() -> new NotFoundException("Student not found with id " + id));
    }

    //Delete User
    public Optional deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return "Delete Successfully!";
                });
    }

    /*Searching and sorting
    public Page<User> getByWord(Optional<String> word,
                                Optional<Integer> page, Optional<String> sortBy) {
        return userRepository.findByWord(word.orElse(" "),
                PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC, sortBy.orElse("id")));
    }*/


    //Search user by name
    public List<User> getByWord(String word, Integer pageNo,
                                Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pagedResult = pageRepository.findByWord(word,paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
    }
}
