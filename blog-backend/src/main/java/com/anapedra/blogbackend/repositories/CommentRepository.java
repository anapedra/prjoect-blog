package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT obj FROM  Comment  obj WHERE obj.dataComment BETWEEN :min AND :max ")
    List<Comment> findComment(LocalDate min, LocalDate max);
}
