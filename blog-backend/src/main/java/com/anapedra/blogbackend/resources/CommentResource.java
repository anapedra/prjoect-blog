package com.anapedra.blogbackend.resources;


import com.anapedra.blogbackend.dtos.CommentDTO;
import com.anapedra.blogbackend.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/comments")
public class CommentResource {

    private final CommentService service;
    public CommentResource(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll(@RequestParam(value = "minDate",defaultValue = "") String minDate,
                                                    @RequestParam(value = "maxDate",defaultValue = "") String maxDate) {
        List<CommentDTO> list = service.findAllPaged(minDate,maxDate);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable Long id) {
        CommentDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> insert(@RequestBody @Valid CommentDTO dto) {
        CommentDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody @Valid CommentDTO dto) {
        CommentDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}




