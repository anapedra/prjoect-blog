package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Category;
import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.entities.Post;


import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PostDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Campo obrigatório")
    private String title;
    @NotBlank(message = "Campo obrigatório!")
    private String text;
    private LocalDate dataPost;
    private LocalDate dataUpdatePost;
    //@NotBlank(message = "Campo obrigatório!")
    private UserAuthorDTO author;
   // @NotBlank(message = "Campo obrigatório!")
    private List<CategoryDTO> categories=new ArrayList<>();
    private List<CommentDTO>comments=new ArrayList<>();

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String text, LocalDate dataPost, LocalDate dataUpdatePost, UserAuthorDTO author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.dataPost = dataPost;
        this.dataUpdatePost = dataUpdatePost;
        this.author = author;
    }
    public PostDTO(Post entity) {
        id=entity.getId();
        title=entity.getTitle();
        text=entity.getText();
        dataPost=entity.getDataPost();
        dataUpdatePost=entity.getDataUpdatePost();
      categories= entity.getCategories().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        author=new UserAuthorDTO(entity.getAuthor().getId(),entity.getAuthor().getFirstName(),entity.getAuthor().getEmail());
       entity.getComments().forEach(comment -> this.comments.add(new CommentDTO(comment)));
    }

    public PostDTO(Post entity, Set<Category> categories,List<Comment>comments) {
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

    public List<CategoryDTO> getCategories() {
        return categories;
    }


    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
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

    public LocalDate getDataPost() {
        return dataPost;
    }

    public void setDataPost(LocalDate dataPost) {
        this.dataPost = dataPost;
    }

    public void setDataUpdatePost(LocalDate dataUpdatePost) {
        this.dataUpdatePost = dataUpdatePost;
    }

    public LocalDate getDataUpdatePost() {
        return dataUpdatePost;
    }

    public UserAuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserAuthorDTO author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", dataPost=" + dataPost +
                ", dataUpdatePost=" + dataUpdatePost +
                ", author=" + author +
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
