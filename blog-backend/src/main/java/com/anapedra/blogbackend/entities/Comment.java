package com.anapedra.blogbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;



import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    @Lob
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataComment;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataUpdateComment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    private List<Reply> repleys = new ArrayList<>();



    public Comment() {

    }

    public Comment(Long id, String text, LocalDate dataComment, LocalDate dataUpdateComment, Post post, User author) {
        this.id = id;
        this.text = text;
        this.dataComment = dataComment;
        this.dataUpdateComment = dataUpdateComment;
        this.post = post;
        this.author = author;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dataComment=" + dataComment +
                ", dataUpdateComment=" + dataUpdateComment +
                ", post=" + post +
                ", author=" + author +
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
