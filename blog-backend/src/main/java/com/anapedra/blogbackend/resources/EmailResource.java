package com.anapedra.blogbackend.resources;

import com.anapedra.blogbackend.dtos.EmailDTO;

import com.anapedra.blogbackend.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailResource {

   private final EmailService emailService;
    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailDTO dto){
        emailService.sendEmail(dto);
        return ResponseEntity.noContent().build();
    }
}
