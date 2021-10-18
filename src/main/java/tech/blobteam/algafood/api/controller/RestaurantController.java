package tech.blobteam.algafood.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.blobteam.algafood.exception.EntityInUseException;
import tech.blobteam.algafood.model.Restaurant;
import tech.blobteam.algafood.repository.restaurant.RestaurantRepository;
import tech.blobteam.algafood.service.restaurant.RestaurantRegisterService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/** @author vinicius-victor */
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

  private final RestaurantRegisterService restaurantRegister;
  private final RestaurantRepository repository;

  @GetMapping
  public ResponseEntity<List<Restaurant>> list() {
    return ResponseEntity.ok(repository.listAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
    Restaurant restaurant = repository.findById(id);

    if (restaurant == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(restaurant);
  }

  @PostMapping
  public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
    final Restaurant savedRestaurant = restaurantRegister.save(restaurant);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Restaurant> delete(@PathVariable Long id) {
    try {
      restaurantRegister.delete(id);
      return ResponseEntity.noContent().build();
    } catch (EntityInUseException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
