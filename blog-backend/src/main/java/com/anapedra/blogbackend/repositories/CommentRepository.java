package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT obj FROM  Comment  obj WHERE obj.dataComment BETWEEN :min AND :max ")
    List<Comment> findComment(LocalDate min, LocalDate max);


    @Query("SELECT DISTINCT obj FROM Comment obj INNER JOIN obj.author aut WHERE " +
            " (COALESCE(:author) IS NULL OR aut IN :author)")
    Page<Comment> findCommentAuthor(User author, Pageable pageable);

}
