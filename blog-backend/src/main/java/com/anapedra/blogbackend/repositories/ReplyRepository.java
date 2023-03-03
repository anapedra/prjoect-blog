package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.Reply;
import com.anapedra.blogbackend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Query("SELECT obj FROM  Reply  obj WHERE obj.dataReply BETWEEN :min AND :max ")
    List<Reply> findReply(LocalDate min, LocalDate max);


    @Query("SELECT DISTINCT obj FROM Reply obj INNER JOIN obj.author aut WHERE " +
            " (COALESCE(:author) IS NULL OR aut IN :author)")
    Page<Reply> findReplyAuthor(User author, Pageable pageable);

}
