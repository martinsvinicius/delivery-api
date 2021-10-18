package tech.blobteam.algafood.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.blobteam.algafood.exception.EntityInUseException;
import tech.blobteam.algafood.model.Kitchen;
import tech.blobteam.algafood.repository.kitchen.KitchenRepository;
import tech.blobteam.algafood.service.kitchen.KitchenRegisterService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/** @author vinicius-victor */
@RestController
@RequestMapping("/kitchens")
@RequiredArgsConstructor
public class KitchenController {

  private final KitchenRepository repository;
  private final KitchenRegisterService kitchenRegister;

  @GetMapping
  public ResponseEntity<List<Kitchen>> list() {
    return ResponseEntity.ok(repository.listAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
    final Kitchen kitchen = repository.findById(id);

    if (kitchen == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(kitchen);
  }

  @PostMapping
  public ResponseEntity<Kitchen> create(@RequestBody Kitchen kitchen) {
    final Kitchen savedKitchen = kitchenRegister.save(kitchen);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedKitchen);
  }

  @PutMapping("{id}")
  public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
    final Kitchen currentKitchen = repository.findById(id);

    if (currentKitchen == null) return ResponseEntity.notFound().build();

    currentKitchen.setName(kitchen.getName());

    final Kitchen savedKitchen = kitchenRegister.save(currentKitchen);

    return ResponseEntity.ok(savedKitchen);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
    try {
      kitchenRegister.delete(id);
      return ResponseEntity.noContent().build();
    } catch (EntityInUseException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
