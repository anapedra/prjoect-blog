package com.anapedra.blogbackend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
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
    private Instant dataReply;
    private Instant dataUpdateReply;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
   // private List<Reply> repleys = new ArrayList<>();


    public Reply(Long id, String title, String text, Instant dataReply, Instant dataUpdateReply, Comment comment) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.dataReply = dataReply;
        this.dataUpdateReply = dataUpdateReply;
        this.comment = comment;
    }

    public Reply() {

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

    public Instant getDataReply() {
        return dataReply;
    }

    public void setDataReply(Instant dataPost) {
        this.dataReply = dataPost;
    }

    public Instant getDataUpdateReply() {
        return dataUpdateReply;
    }

    public void setDataUpdateReply(Instant dataUpdatePost) {
        this.dataUpdateReply = dataUpdatePost;
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
                ", dataPost=" + dataReply +
                ", dataUpdatePost=" + dataUpdateReply +
                ", comment=" + comment +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        Reply repley = (Reply) o;
        return Objects.equals(id, repley.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
