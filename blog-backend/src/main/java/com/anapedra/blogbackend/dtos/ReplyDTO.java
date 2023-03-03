package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Reply;


import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class ReplyDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String text;
    private LocalDate dataReply;
    private LocalDate dataUpdateReply;
  //  @NotBlank(message = "Campo obrigatório!")
    private UserAuthorDTO author;
    private final Set<ReplyDTO> replies=new HashSet<>();

    public ReplyDTO() {
    }

    public ReplyDTO(Long id, String text, LocalDate dataReply, LocalDate dataUpdateReply,UserAuthorDTO author) {
        this.id = id;
        this.text = text;
        this.dataReply = dataReply;
        this.dataUpdateReply = dataUpdateReply;
        this.author = author;

    }

    public ReplyDTO(Reply entity) {
        id= entity.getId();
        text= entity.getText();
        dataReply=entity.getDataReply();
        dataUpdateReply=entity.getDataUpdateReply();
        author=new UserAuthorDTO(entity.getAuthor().getId(),entity.getAuthor().getFirstName(),entity.getAuthor().getEmail());
        entity.getRepleys().forEach(reply -> this.replies.add(new ReplyDTO(reply)));
    }

    public Set<ReplyDTO> getReplies() {
        return replies;
    }

    public Long getId() {
        return id;
    }

    public UserAuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserAuthorDTO author) {
        this.author = author;
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

    public LocalDate getDataReply() {
        return dataReply;
    }

    public void setDataReply(LocalDate dataReply) {
        this.dataReply = dataReply;
    }

    public LocalDate getDataUpdateReply() {
        return dataUpdateReply;
    }

    public void setDataUpdateReply(LocalDate dataUpdateReply) {
        this.dataUpdateReply = dataUpdateReply;
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dataReply=" + dataReply +
                ", dataUpdateReply=" + dataUpdateReply +
                ", author=" + author +
                ", replies=" + replies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReplyDTO)) return false;
        ReplyDTO replyDTO = (ReplyDTO) o;
        return Objects.equals(id, replyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



