package com.example.user.model;

import com.example.user.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_management")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Integer userId=new Random().nextInt(10000);
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(name = "user_encrypted_password",nullable = false)
    private String encryptedPassword;

    @Enumerated(EnumType.STRING)
    @Column(name="user_type",nullable = false)
    private RoleType userRole;

}
