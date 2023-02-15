package com.anapedra.blogbackend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private Instant dataComment;
    private Instant dataUpdateComment;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToMany(mappedBy = "comment",fetch = FetchType.EAGER)
    private List<Repley> repleys = new ArrayList<>();


    public Comment(Long id, String title, String text, Instant dataComment, Instant dataUpdateComment) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.dataComment = dataComment;
        this.dataUpdateComment = dataUpdateComment;
    }

    public Comment() {

    }

    public List<Repley> getRepleys() {
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

    public Instant getDataComment() {
        return dataComment;
    }

    public void setDataComment(Instant dataComment) {
        this.dataComment = dataComment;
    }

    public Instant getDataUpdateComment() {
        return dataUpdateComment;
    }

    public void setDataUpdateComment(Instant dataUpdateComment) {
        this.dataUpdateComment = dataUpdateComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", dataComment=" + dataComment +
                ", dataUpdateComment=" + dataUpdateComment +
                ", repleys=" + repleys +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
