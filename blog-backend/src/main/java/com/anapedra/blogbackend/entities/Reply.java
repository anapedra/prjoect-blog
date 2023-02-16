package com.anapedra.blogbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_reply")
public class Reply implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataReply;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataUpdateReply;
    @OneToMany
    private List<Reply> repleys = new ArrayList<>();


    public Reply(Long id, String title, String text, Comment comment) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.comment = comment;
    }

    public void setDataReply(LocalDate dataReply) {
        this.dataReply = dataReply;
    }

    public void setDataUpdateReply(LocalDate dataUpdateReply) {
        this.dataUpdateReply = dataUpdateReply;
    }

    public Reply() {

    }

    public LocalDate getDataReply() {
        return dataReply;
    }

    public LocalDate getDataUpdateReply() {
        return dataUpdateReply;
    }

    public List<Reply> getRepleys() {
        return repleys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", comment=" + comment +
                ", dataReply=" + dataReply +
                ", dataUpdateReply=" + dataUpdateReply +
                ", repleys=" + repleys +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        Reply reply = (Reply) o;
        return Objects.equals(getId(), reply.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

