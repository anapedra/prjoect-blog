package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Category;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.Role;
import com.anapedra.blogbackend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("SELECT DISTINCT obj FROM User obj INNER JOIN obj.roles rol WHERE " +
            " (COALESCE(:roles) IS NULL OR rol IN :roles)")
    Page<User> findUserRole(List<Role> roles, Pageable pageable);


}
