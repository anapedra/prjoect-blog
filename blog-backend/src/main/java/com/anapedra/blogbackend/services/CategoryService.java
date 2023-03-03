package com.anapedra.blogbackend.services;

import com.anapedra.blogbackend.dtos.CategoryDTO;
import com.anapedra.blogbackend.entities.Category;
import com.anapedra.blogbackend.repositories.CategoryRepository;
import com.anapedra.blogbackend.repositories.UserRepository;
import com.anapedra.blogbackend.services.exeptuonservice.DatabaseException;
import com.anapedra.blogbackend.services.exeptuonservice.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AuthService authService;

    public CategoryService(CategoryRepository categoryRepository, AuthService authService, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
      public List<CategoryDTO> findAll(){
        //Publico
        List<Category> list=categoryRepository.findAll();
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        //Publico
        Optional<Category> obj=categoryRepository.findById(id);
        Category entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new CategoryDTO(entity,entity.getPosts());
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        authService.validateAdmin();
        var category=new Category();
        category.setName(dto.getName());
        category=categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO){
        try {
            authService.validateAdmin();
            var category= categoryRepository.getOne(id);
            category.setName(categoryDTO.getName());
            category=categoryRepository.save(category);
            return new CategoryDTO(category);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }
    @Transactional
    public void deleteById(Long id){

        try {
            authService.validateAdmin();
            categoryRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }

    }

}