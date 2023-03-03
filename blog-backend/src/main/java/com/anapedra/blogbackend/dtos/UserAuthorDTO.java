package com.anapedra.blogbackend.dtos;


import com.anapedra.blogbackend.entities.User;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class UserAuthorDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String firstName;
    @Email(message = "Email invalido!")
    private String email;

    public UserAuthorDTO(Long id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    public UserAuthorDTO() {

    }

    public UserAuthorDTO(User entity) {
     id=entity.getId();
     firstName=entity.getFirstName();
     email=entity.getEmail();
    }

    @Override
    public String toString() {
        return "UserAuthorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthorDTO)) return false;
        UserAuthorDTO authorDTO = (UserAuthorDTO) o;
        return Objects.equals(id, authorDTO.id) && Objects.equals(firstName, authorDTO.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }
}




