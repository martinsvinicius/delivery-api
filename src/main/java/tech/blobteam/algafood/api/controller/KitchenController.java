package tech.blobteam.algafood.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.blobteam.algafood.model.Kitchen;
import tech.blobteam.algafood.repository.kitchen.KitchenRepository;

import java.util.List;

/** @author vinicius-victor */
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

  private final KitchenRepository repository;

  public KitchenController(KitchenRepository kitchenRepository) {
    repository = kitchenRepository;
  }

  @GetMapping
  public List<Kitchen> list() {
    return repository.listAll();
  }

  @GetMapping("/{id}")
  public Kitchen find(@PathVariable Long id) {
    return repository.findById(id);
  }
}
