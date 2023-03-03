package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Category;
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
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT obj FROM  Post  obj WHERE obj.dataPost BETWEEN :min AND :max ")
    Page<Post> findPost(LocalDate min, LocalDate max, Pageable pageable);

    @Query("SELECT DISTINCT obj FROM Post obj INNER JOIN obj.categories cats WHERE " +
            " (COALESCE(:categories) IS NULL OR cats IN :categories)")
    Page<Post> find(List<Category> categories, Pageable pageable);

    @Query("SELECT obj FROM Post obj JOIN FETCH obj.categories WHERE obj IN :posts")
    List<Post> findAllPost(List<Post>posts);

    @Query("SELECT DISTINCT obj FROM Post obj INNER JOIN obj.author aut WHERE " +
            " (COALESCE(:author) IS NULL OR aut IN :author)")
    Page<Post> findPostAuthor(User author, Pageable pageable);

}


