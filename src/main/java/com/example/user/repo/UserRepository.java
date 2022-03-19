package com.example.user.repo;

import com.example.user.model.User;
import com.example.user.role.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query
    public User findByEmail(String email);

    @Query
    public User findByEmailAndPasswordAndUserRole(String email, String password, RoleType role);
}
