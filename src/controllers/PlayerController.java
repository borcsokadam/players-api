package src.controllers;

import services.PlayerService;

@RestController
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/player")
    public ResponseEntity<?> index() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPlayers());
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<?> show(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPlayerById(id));
    }

    @PostMapping("/player")
    public ResponseEntity<?> create(@RequestBody @Validated PlayerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPlayer(request));
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id, @RequestBody @Validated PlayerRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateById(id, request));
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        service.deletePlayerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
