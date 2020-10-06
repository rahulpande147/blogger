package com.blogs.blogger.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Login {

    @Id
    public Long id;
    public String username;
    public String role;
    public String password;
    public Boolean active;

    /*@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "users_username", nullable = false)*/

    @OneToOne
    @MapsId
    @JsonIgnore
    //@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private User user;

    public Login() {
    }


    public Login(Long id, String username, String role, String password, Boolean enabled , User user) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.active = enabled;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return true;
    }

    public void setEnabled(Boolean enabled) {
        this.active = enabled;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }
}


/*<!--Security-->
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-test</artifactId>
<scope>test</scope>
</dependency>
<!--Security
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-config</artifactId>
</dependency>-->*/