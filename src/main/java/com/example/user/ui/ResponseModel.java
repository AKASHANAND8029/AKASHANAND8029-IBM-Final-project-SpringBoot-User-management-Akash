package com.example.user.ui;

import com.example.user.role.RoleType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseModel {
    private String email;
    private String userName;
    private RoleType userRole;
}
