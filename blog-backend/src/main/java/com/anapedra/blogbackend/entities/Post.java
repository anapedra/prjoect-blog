package com.anapedra.blogbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tb_post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    private String title;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataPost;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dataUpdatePost;
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Comment> comments=new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "categoruy_id"))
    private Set<Category> categories=new HashSet<>();


    public Post(Long id, User author, String title, String text) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;

    }

    public Post() {

    }

    public List<Comment> getComments() {
        return comments;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public LocalDate getDataPost() {
        return dataPost;
    }

    public void setDataPost(LocalDate dataPost) {
        this.dataPost = dataPost;
    }

    public LocalDate getDataUpdatePost() {
        return dataUpdatePost;
    }

    public void setDataUpdatePost(LocalDate dataUpdatePost) {
        this.dataUpdatePost = dataUpdatePost;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", dataPost=" + dataPost +
                ", dataUpdatePost=" + dataUpdatePost +
                ", comments=" + comments +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
