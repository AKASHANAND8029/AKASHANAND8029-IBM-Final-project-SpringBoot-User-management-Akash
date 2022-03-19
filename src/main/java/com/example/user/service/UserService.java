package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.model.User;
import com.example.user.role.RoleType;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUser();
    UserDto findUserByEmail(String email);
    void delete(String email);
    UserDto validateUserByEmail(String email);
    User findByEmailAndPasswordAndUserRole(String email,String password,RoleType role);

}
