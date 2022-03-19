package com.example.user.ui;

import com.example.user.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RequestModel {
    private String email;
    private String userName;
    private String password;
    private RoleType userRole;
}
