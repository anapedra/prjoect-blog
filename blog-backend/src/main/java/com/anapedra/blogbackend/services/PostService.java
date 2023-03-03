package com.anapedra.blogbackend.services;

import com.anapedra.blogbackend.dtos.CategoryDTO;
import com.anapedra.blogbackend.dtos.PostDTO;
import com.anapedra.blogbackend.entities.Category;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.User;
import com.anapedra.blogbackend.repositories.CategoryRepository;
import com.anapedra.blogbackend.repositories.PostRepository;
import com.anapedra.blogbackend.repositories.UserRepository;
import com.anapedra.blogbackend.services.exeptuonservice.DatabaseException;
import com.anapedra.blogbackend.services.exeptuonservice.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository,
                       AuthService authService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public Page<PostDTO> findPost(String minDate, String maxDate, Pageable pageable){
        //Público
        LocalDate today=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min =minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max=maxDate.equals("") ? today : LocalDate.parse(maxDate);
        Page<Post> page=postRepository.findPost(min,max,pageable);
        postRepository.findAllPost(page.stream().collect(Collectors.toList()));
        return page.map(PostDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<PostDTO> findAllByCategory(Long categoryId, Pageable pageable) {
        //Público
        List<Category>list=(categoryId == 0) ? null : List.of(categoryRepository.getOne(categoryId));
        Page<Post> page= postRepository.find(list,pageable);
        return page.map(PostDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<PostDTO> findAllByAuthor(Long authorId, Pageable pageable) {
        //Público
        User author=(authorId == 0) ? null : userRepository.getOne(authorId);
        Page<Post> page= postRepository.findPostAuthor(author,pageable);
        return page.map(PostDTO::new);
    }
    @Transactional(readOnly = true)
    public PostDTO findById(Long id){
        //Púplico
        Optional<Post> obj=postRepository.findById(id);
        Post entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new PostDTO(entity);
    }

    @Transactional
    public PostDTO insert(PostDTO dto) {
        authService.validateAdmin();
        var post=new Post();
        copyDtoToEntity(dto,post);
        post=postRepository.save(post);
        return new PostDTO(post);
    }
    @Transactional
    public PostDTO update(Long id, PostDTO postDTO){
        try {
            authService.validateSelf(id);
            var post=postRepository.getOne(id);
            copyDtoToEntity(postDTO,post);
            post=postRepository.save(post);
            return new PostDTO(post);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }
    @Transactional
    public void deleteById(Long id){

        try {
            authService.validateAdmin();
            postRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(" Integrity violation");
        }
    }
    private void copyDtoToEntity(PostDTO postDTO,Post post){
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setDataPost(LocalDate.now());
        post.setDataUpdatePost(LocalDate.now());

        var author=new User();
        author.setId(postDTO.getAuthor().getId());
        post.setAuthor(author);

        post.getCategories().clear();
        for (CategoryDTO catDto : postDTO.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            post.getCategories().add(category);
        }

    }

}


