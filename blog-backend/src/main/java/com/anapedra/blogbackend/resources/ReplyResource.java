package com.anapedra.blogbackend.resources;

@RestController
@RequestMapping(value = "/replies")
public class ReplyController {

    private final ReplyService service;
    public ReplyController(ReplyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReplyDTO>> findAll(  @RequestParam(value = "minDate",defaultValue = "") String minDate,
                                                    @RequestParam(value = "maxDate",defaultValue = "") String maxDate) {
        List<ReplyDTO> list = service.findAll(minDate,maxDate);
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

