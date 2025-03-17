package com.tech.UserService.Entity;


import jakarta.persistence.*;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Data
@Table( name ="user")
//@RedisHash("User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
}
