package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Query("SELECT obj FROM  Reply  obj WHERE obj.dataReply BETWEEN :min AND :max ")
    List<Reply> findReply(LocalDate min, LocalDate max);
}
