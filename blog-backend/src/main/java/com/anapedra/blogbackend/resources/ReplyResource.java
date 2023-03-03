package com.anapedra.blogbackend.resources;

import com.anapedra.blogbackend.dtos.ReplyDTO;
import com.anapedra.blogbackend.services.ReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/replies")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ReplyResource {

    private final ReplyService service;
    public ReplyResource(ReplyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReplyDTO>> findAll(@RequestParam(value = "minDate",defaultValue = "") String minDate,
                                                  @RequestParam(value = "maxDate",defaultValue = "") String maxDate) {
        List<ReplyDTO> list = service.findAll(minDate,maxDate);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/authors")
    public ResponseEntity<Page<ReplyDTO>> findPostsByAuthor(@RequestParam(value = "authorId",defaultValue = "0") Long authorId, Pageable pageable) {
        Page<ReplyDTO> list = service.findAllByAuthor(authorId,pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReplyDTO> findById(@PathVariable Long id) {
        ReplyDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ReplyDTO> insert(@RequestBody @Valid ReplyDTO dto) {
        ReplyDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReplyDTO> update(@PathVariable Long id, @RequestBody @Valid ReplyDTO dto) {
        ReplyDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

