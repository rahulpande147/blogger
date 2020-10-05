package com.blogs.blogger.daoimpl;

import com.blogs.blogger.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);

    @Query("select s from User s where s.name like %?1%")
    Page<User> findByWord (String fname, Pageable pageable);

    //Optional<User> findByUsername(String username);
}
