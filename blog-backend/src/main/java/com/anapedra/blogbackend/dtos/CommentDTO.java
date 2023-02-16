package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.Reply;

import javax.persistence.Lob;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    @Lob
    private String text;
    private List<ReplyDTO> replies=new ArrayList<>();
    private PostDTO post;



    public CommentDTO(){

    }

    public CommentDTO(Long id, String text, PostDTO post) {
        this.id = id;
        this.text = text;
        this.post = post;
    }

    public CommentDTO(Comment entity) {
        id =entity.getId();
        text = entity.getText();
        post=new PostDTO(new Post());
        entity.getRepleys().forEach(reply -> this.replies.add(new ReplyDTO(reply)));
    }

    public CommentDTO(Comment entity, List<Reply> replies) {
        this(entity);
        replies.forEach(r -> this.replies.add(new ReplyDTO(r)));
    }

    public PostDTO getPost() {
        return post;
    }
    public void setPost(PostDTO post) {
        this.post = post;
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", replies=" + replies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}


