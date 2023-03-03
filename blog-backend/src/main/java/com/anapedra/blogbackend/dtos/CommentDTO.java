package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.Reply;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    @NotBlank(message = "Campo obrigatório!")
    private String text;
    private LocalDate dataComment;
    private LocalDate dataUpdateComment;
  //  @NotBlank(message = "Campo obrigatório!")
    private UserAuthorDTO author;
    private List<ReplyDTO> replies=new ArrayList<>();


    public CommentDTO(){

    }

    public CommentDTO(Long id, String text, LocalDate dataComment, LocalDate dataUpdateComment,UserAuthorDTO author) {
        this.id = id;
        this.text = text;
        this.dataComment = dataComment;
        this.dataUpdateComment = dataUpdateComment;
        this.author = author;
    }

    public CommentDTO(Comment entity) {
        id =entity.getId();
        text = entity.getText();
        dataComment = entity.getDataComment();
        dataUpdateComment = entity.getDataUpdateComment();
        author=new UserAuthorDTO(entity.getAuthor().getId(),entity.getAuthor().getFirstName(),entity.getAuthor().getEmail());

    }

    public CommentDTO(Comment entity, List<Reply> replies) {
        this(entity);
        replies.forEach(r -> this.replies.add(new ReplyDTO(r)));
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }


    public UserAuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserAuthorDTO author) {
        this.author = author;
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

    public LocalDate getDataComment() {
        return dataComment;
    }

    public void setDataComment(LocalDate dataComment) {
        this.dataComment = dataComment;
    }

    public LocalDate getDataUpdateComment() {
        return dataUpdateComment;
    }

    public void setDataUpdateComment(LocalDate dataUpdateComment) {
        this.dataUpdateComment = dataUpdateComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO dto = (CommentDTO) o;
        return Objects.equals(getId(), dto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dataComment=" + dataComment +
                ", dataUpdateComment=" + dataUpdateComment +
                ", author=" + author +
                ", replies=" + replies +
                '}';
    }
}
