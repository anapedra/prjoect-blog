package com.anapedra.blogbackend.services;

import com.anapedra.blogbackend.dtos.RoleDTO;
import com.anapedra.blogbackend.entities.Role;
import com.anapedra.blogbackend.repositories.RoleRepository;
import com.anapedra.blogbackend.services.exeptuonservice.DatabaseException;
import com.anapedra.blogbackend.services.exeptuonservice.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository repository;
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public RoleDTO findById(Long id){
        Optional<Role> obj=repository.findById(id);
        Role entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new RoleDTO(entity);
    }
    @Transactional
    public RoleDTO save(RoleDTO dto) {
        var role=new Role();
        role.setAuthority(dto.getAuthority());
        role=repository.save(role);
        return new RoleDTO(role);
    }
    @Transactional
    public RoleDTO upDate(Long id, RoleDTO dto){
        try {
            var role = repository.getOne(id);
            role.setAuthority(dto.getAuthority());
            role=repository.save(role);
            return new RoleDTO(role);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }

    public void delet(Long id){

        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }

    }
}
