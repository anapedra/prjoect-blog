package com.anapedra.blogbackend.repositories;

import com.anapedra.blogbackend.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
