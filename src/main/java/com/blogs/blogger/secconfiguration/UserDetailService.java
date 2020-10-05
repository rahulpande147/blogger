package com.blogs.blogger.secconfiguration;

import com.blogs.blogger.daoimpl.LoginRepository;
import com.blogs.blogger.daoimpl.UserRepository;
import com.blogs.blogger.models.Login;
import com.blogs.blogger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> login = loginRepository.findByUsername(username);

        login.orElseThrow(()-> new UsernameNotFoundException("User not found "+username));

        return login.map(UserDetail :: new).get();

    }
}
