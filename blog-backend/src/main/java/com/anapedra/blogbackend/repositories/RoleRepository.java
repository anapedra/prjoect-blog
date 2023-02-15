package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
