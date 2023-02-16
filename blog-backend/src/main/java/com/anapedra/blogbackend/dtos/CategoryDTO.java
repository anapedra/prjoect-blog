package com.anapedra.blogbackend.dtos;

import com.anapedra.blogbackend.entities.Category;
import com.anapedra.blogbackend.entities.Post;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String name;
    private Set<PostDTO> posts = new HashSet<>();

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public CategoryDTO(Category entity) {
        id=entity.getId();
        name= entity.getName();
       entity.getPosts().forEach(post -> this.posts.add(new PostDTO(post)));
    }

    public CategoryDTO(Category entity, Set<Post> posts) {
        this(entity);
        posts.forEach(post -> this.posts.add(new PostDTO(post)));
    }

    public Set<PostDTO> getPosts() {
        return posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO)) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
