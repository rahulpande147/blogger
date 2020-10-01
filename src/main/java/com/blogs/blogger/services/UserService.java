package com.blogs.blogger.services;


import com.blogs.blogger.daoimpl.UserRepository;
import com.blogs.blogger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Get all user
    public List<User> getAllUser (){
        return userRepository.findAll();
    }

    //Find By Id
    public User getUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    //create new user
    public User createUser(User user){
        return userRepository.save(user);
    }


    public User updateUser( Long id,
                                 User userNew) {
        return userRepository.findById(id)
                .map(student -> {
                    student.setName(userNew.getName());
                    return userRepository.save(student);
                }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    //Delete User
    public Optional deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return "Delete Successfully!";
                });
    }





    /*public User updateUser(int id,
                                 User userNew) {
        return userRepository.findById(id)
                .map( user -> {
                user.setName}
    }*/
}
