package com.anapedra.blogbackend.resources;

import com.anapedra.blogbackend.dtos.PostDTO;
import com.anapedra.blogbackend.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/posts")
@CrossOrigin(origins = "*",maxAge = 3600)
public class PostResource {

    private final PostService service;
    public PostResource(PostService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> findAll(
            @RequestParam(value = "minDate",defaultValue = "") String minDate,
            @RequestParam(value = "maxDate",defaultValue = "") String maxDate,
            Pageable pageable) {
        Page<PostDTO> list = service.findPost(minDate,maxDate,pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<Page<PostDTO>> findPostsByCategory(@RequestParam(value = "categoryId",defaultValue = "0") Long categoryId,Pageable pageable) {
        Page<PostDTO> list = service.findAllByCategory(categoryId,pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/authors")
    public ResponseEntity<Page<PostDTO>> findPostsByAuthor(@RequestParam(value = "authorId",defaultValue = "0") Long authorId, Pageable pageable) {
        Page<PostDTO> list = service.findAllByAuthor(authorId,pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable Long id) {
        PostDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PostDTO> insert(@RequestBody @Valid PostDTO dto) {
        PostDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable Long id, @RequestBody @Valid PostDTO dto) {
        PostDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}




