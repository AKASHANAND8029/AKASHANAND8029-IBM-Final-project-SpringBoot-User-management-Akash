package com.example.user.model;

import com.example.user.role.RoleType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "This field can not be empty")
    private String email;

    @Column(name = "user_name",nullable = false,unique = true)
    @NotEmpty(message = "This field can not be empty")
    private String userName;

    @Column(name = "user_encrypted_password",nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have min 6 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="user_type",nullable = false)
    private RoleType userRole;

}
