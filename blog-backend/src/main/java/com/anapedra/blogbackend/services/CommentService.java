package com.anapedra.blogbackend.services;

import com.anapedra.blogbackend.dtos.CommentDTO;
import com.anapedra.blogbackend.entities.Comment;
import com.anapedra.blogbackend.repositories.CommentRepository;
import com.anapedra.blogbackend.repositories.ReplyRepository;
import com.anapedra.blogbackend.services.exeptuonservice.DatabaseException;
import com.anapedra.blogbackend.services.exeptuonservice.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final ReplyRepository replyRepository;
    private final AuthService authService;
    public CommentService(CommentRepository commentRepository, ReplyRepository replyRepository, AuthService authService) {
        this.commentRepository = commentRepository;
        this.replyRepository = replyRepository;
        this.authService = authService;
    }



    @Transactional(readOnly = true)
    public List<CommentDTO> findAllPaged(String minDate, String maxDate){
       // authService.validateSelfOrAdmin();//REFATOR E TORNAR MÁTODO PUBLICO
        LocalDate today=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min =minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max=maxDate.equals("") ? today : LocalDate.parse(maxDate);
        List<Comment> list=commentRepository.findComment(min,max);
        return list.stream().map(x->new CommentDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentDTO findById(Long id){

        Optional<Comment> obj=commentRepository.findById(id);
        Comment entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new CommentDTO(entity,entity.getRepleys());
    }

    @Transactional
    public CommentDTO insert(CommentDTO dto) {
       // authService.validateSelfOrAdmin();
        var comment=new Comment();
        copyDtoToEntity(dto,comment);
        comment=commentRepository.save(comment);
        return new CommentDTO(comment);
    }

    @Transactional
    public CommentDTO update(Long id, CommentDTO commentDTO){

        try {
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

    }


}



