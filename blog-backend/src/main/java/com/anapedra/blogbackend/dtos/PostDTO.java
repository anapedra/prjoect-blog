package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Category;
import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.User;

import java.io.Serializable;
import java.util.*;

public class PostDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String title;
    private String text;
    private UserAuthorDTO author;
    private List<CommentDTO> comments=new ArrayList<>();
    private Set<CategoryDTO> categories=new HashSet<>();

    public PostDTO() {
    }

    public PostDTO(Long id, UserAuthorDTO author, String title, String text) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;

    }
    public PostDTO(Post entity) {
        id=entity.getId();
        title=entity.getTitle();
        text=entity.getText();
        author=new UserAuthorDTO(entity.getAuthor().getId(),entity.getAuthor().getFirstName());
        entity.getCategories().forEach(cat -> this.categories.add(new CategoryDTO(cat)));
        entity.getCategories().forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }

    public PostDTO(Post entity, Set<Category> categories, List<Comment> comments) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
        comments.forEach(c -> this.comments.add(new CommentDTO(c)));

    }

    public PostDTO(Post entity, Set<Category> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));

    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserAuthorDTO author) {
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

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDTO)) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(id, postDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
