package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT obj FROM  Post  obj WHERE obj.dataPost BETWEEN :min AND :max ")
    Page<Post> findPost(LocalDate min, LocalDate max, Pageable pageable);

}
