package com.blogs.blogger.daoimpl;

import com.blogs.blogger.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface   LoginRepository extends JpaRepository<Login, Long > {

    Optional<Login> findByUsername(String username);

}
