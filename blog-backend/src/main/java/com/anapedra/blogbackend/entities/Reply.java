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
    @Lob
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataReply;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataUpdateReply;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reply> repleys = new ArrayList<>();

    public Reply(Long id, String text, User author, Comment comment, LocalDate dataReply, LocalDate dataUpdateReply) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.comment = comment;
        this.dataReply = dataReply;
        this.dataUpdateReply = dataUpdateReply;
    }

    public Reply() {

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        Reply reply = (Reply) o;
        return Objects.equals(id, reply.id) && Objects.equals(author, reply.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author);
    }


    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", author=" + author +
                ", comment=" + comment +
                ", dataReply=" + dataReply +
                ", dataUpdateReply=" + dataUpdateReply +
                ", repleys=" + repleys +
                '}';
    }


}




