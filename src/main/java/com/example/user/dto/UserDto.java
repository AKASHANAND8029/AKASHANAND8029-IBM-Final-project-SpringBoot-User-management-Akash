package com.example.user.dto;

import com.example.user.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDto {
    private String email;
    private String userName;
    private String password;
    private RoleType userRole;
}
