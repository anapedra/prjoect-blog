package com.anapedra.blogbackend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
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
    private Instant dataPost;
    private Instant dataUpdatePost;
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Comment> comments=new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "categoruy_id"))
    private Set<Category> categories=new HashSet<>();

    public Post(Long id, User author, String title, String text, Instant dataPost, Instant dataUpdatePost) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.dataPost = dataPost;
        this.dataUpdatePost = dataUpdatePost;
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

    public Instant getDataPost() {
        return dataPost;
    }

    public void setDataPost(Instant dataPost) {
        this.dataPost = dataPost;
    }

    public Instant getDataUpdatePost() {
        return dataUpdatePost;
    }

    public void setDataUpdatePost(Instant dataUpdatePost) {
        this.dataUpdatePost = dataUpdatePost;
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
