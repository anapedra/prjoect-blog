package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Reply;

import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReplyDTO {

    private Long id;
    @Lob
    @Size(min = 1000, max = 5000, message = "O texto prescisa ter no minimo 1000 caracteres e no maximo 5000")
    private String text;
    private List<ReplyDTO>replies=new ArrayList<>();

    public ReplyDTO() {
    }

    public ReplyDTO(Long id, String text) {
        this.id = id;
        this.text = text;

    }

    public ReplyDTO(Reply entity) {
        id= entity.getId();
        text= entity.getText();
        entity.getRepleys().forEach(reply -> this.replies.add(new ReplyDTO(reply)));
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
        return "ReplyDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
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



