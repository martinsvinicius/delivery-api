package tech.blobteam.algafood.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.blobteam.algafood.exception.EntityInUseException;
import tech.blobteam.algafood.model.State;
import tech.blobteam.algafood.repository.state.StateRepository;
import tech.blobteam.algafood.service.state.StateRegisterService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/** @author vinicius-victor */
@RestController
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController {

  private final StateRepository repository;
  private final StateRegisterService registerService;

  @GetMapping
  public ResponseEntity<List<State>> list() {
    return ResponseEntity.ok(repository.listAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<State> findById(@PathVariable Long id) {
    State state = repository.findById(id);

    if (state == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(state);
  }

  @PostMapping
  public ResponseEntity<State> create(@RequestBody State state) {
    State savedState = registerService.save(state);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedState);
  }

  @PutMapping("{id}")
  public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
    final State currentState = repository.findById(id);

    if (currentState == null) return ResponseEntity.notFound().build();

    state.setId(currentState.getId());

    final State savedState = registerService.save(state);

    return ResponseEntity.ok(savedState);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    State stateExists = repository.findById(id);

    if (stateExists == null) return ResponseEntity.notFound().build();

    try {
      registerService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (EntityInUseException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
