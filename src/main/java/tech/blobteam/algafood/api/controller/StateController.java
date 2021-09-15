package tech.blobteam.algafood.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.blobteam.algafood.model.State;
import tech.blobteam.algafood.repository.state.StateRepository;

import java.util.List;

/** @author vinicius-victor */
@RestController
@RequestMapping("/states")
public class StateController {
  private final StateRepository repository;

  public StateController(StateRepository stateRepository) {
    repository = stateRepository;
  }

  @GetMapping
  public List<State> list() {
    return repository.listAll();
  }

  @GetMapping("/{id}")
  public State find(@PathVariable Long id) {
    return repository.findById(id);
  }
}
