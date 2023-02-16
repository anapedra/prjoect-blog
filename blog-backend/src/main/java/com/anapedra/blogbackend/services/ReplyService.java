package com.anapedra.blogbackend.services;

import com.anapedra.blogbackend.dtos.ReplyDTO;
import com.anapedra.blogbackend.entities.Reply;
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
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    public ReplyService(ReplyRepository replyRepository, CommentRepository commentRepository, AuthService authService) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public List<ReplyDTO> findAll(String minDate, String maxDate){

        LocalDate today=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min =minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max=maxDate.equals("") ? today : LocalDate.parse(maxDate);
        List<Reply> list=replyRepository.findReply(min,max);
        return list.stream().map(x -> new ReplyDTO(x)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ReplyDTO findById(Long id){

        Optional<Reply> obj=replyRepository.findById(id);
        Reply entity=obj.orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" not found"));
        return new ReplyDTO(entity);
    }

    @Transactional
    public ReplyDTO insert(ReplyDTO dto) {

        var reply=new Reply();
        copyDtoToEntity(dto,reply);
        reply=replyRepository.save(reply);
        return new ReplyDTO(reply);
    }

    @Transactional
    public ReplyDTO update(Long id, ReplyDTO replyDTO){
        try {
            var reply=replyRepository.getOne(id);
            copyDtoToEntity(replyDTO,reply);
            reply=replyRepository.save(reply);
            return new ReplyDTO(reply);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }

    }
    @Transactional
    public void deleteById(Long id){

        try {
            replyRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }

    }

    private void copyDtoToEntity(ReplyDTO replyDTO,Reply reply){
        reply.setText(replyDTO.getText());
        reply.setDataReply(LocalDate.now());
        reply.setDataUpdateReply(LocalDate.now());
    }


}
