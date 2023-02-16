package com.anapedra.blogbackend.dtos;


import com.anapedra.blogbackend.entities.User;

import java.io.Serializable;
import java.util.Objects;

public class UserAuthorDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String firstName;


    private UserAuthorDTO(){

    }

    public UserAuthorDTO(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;

    }
    private UserAuthorDTO(User entity){
        id=entity.getId();
        firstName=entity.getFirstName();


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "UserAuthorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthorDTO)) return false;
        UserAuthorDTO that = (UserAuthorDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

