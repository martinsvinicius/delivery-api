package tech.blobteam.algafood.service.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tech.blobteam.algafood.exception.EntityInUseException;
import tech.blobteam.algafood.model.Restaurant;
import tech.blobteam.algafood.repository.restaurant.RestaurantRepository;

import javax.persistence.EntityNotFoundException;

/** @author vinicius-victor */
@Service
@RequiredArgsConstructor
public class RestaurantRegisterService {

  final RestaurantRepository repository;

  public Restaurant save(Restaurant restaurant) {
    return repository.save(restaurant);
  }

  public void delete(Long id) {
    try {
      repository.remove(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException("Restaurant not found");
    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException("Restaurant cannot be removed because it's currently in use");
    }
  }
}
