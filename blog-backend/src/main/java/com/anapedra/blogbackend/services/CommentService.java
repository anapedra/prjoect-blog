package com.anapedra.blogbackend.services;

import com.anapedra.blogbackend.dtos.CommentDTO;
import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.entities.Post;
import com.anapedra.blogbackend.entities.User;
import com.anapedra.blogbackend.repositories.CommentRepository;
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
public class CommentService {



    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    public CommentService(CommentRepository commentRepository, AuthService authService, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CommentDTO> findAllPaged(String minDate, String maxDate){
        //Público
        LocalDate today=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min =minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max=maxDate.equals("") ? today : LocalDate.parse(maxDate);
        List<Comment> list=commentRepository.findComment(min,max);
        return list.stream().map(CommentDTO::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    //Público
    public Page<CommentDTO> findAllByAuthor(Long authorId, Pageable pageable) {
        User author=(authorId == 0) ? null : userRepository.getOne(authorId);
        Page<Comment> page= commentRepository.findCommentAuthor(author,pageable);
        return page.map(CommentDTO::new);
    }

    @Transactional(readOnly = true)
    public CommentDTO findById(Long id){
        //Publico
        Optional<Comment> obj=commentRepository.findById(id);
        Comment entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new CommentDTO(entity,entity.getRepleys());
    }

    @Transactional
    public CommentDTO insert(CommentDTO dto) {
        //Qualquer logado!
        var comment=new Comment();
        copyDtoToEntity(dto,comment);
        comment=commentRepository.save(comment);
        return new CommentDTO(comment);
    }

    @Transactional
    public CommentDTO update(Long id, CommentDTO commentDTO){

        try {
            authService.validateSelf(id);
            var comment= commentRepository.getOne(id);
            copyDtoToEntity(commentDTO,comment);
            comment=commentRepository.save(comment);
            return new CommentDTO(comment);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }

    @Transactional
    public void deleteById(Long id){
        try {
           authService.validateSelfOrAdmin(id);
            commentRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }

    }

    private void copyDtoToEntity(CommentDTO commentDTO,Comment comment){
        comment.setText(commentDTO.getText());
        comment.setDataComment(LocalDate.now());
        comment.setDataUpdateComment(LocalDate.now());

        var author=new User();
        author.setId(commentDTO.getAuthor().getId());
        comment.setAuthor(author);


        var post=new Post();
        comment.setId(commentDTO.getId());
        comment.setId(post.getId());


    }


}



