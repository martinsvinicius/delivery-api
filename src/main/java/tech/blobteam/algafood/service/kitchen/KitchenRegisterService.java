package tech.blobteam.algafood.service.kitchen;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tech.blobteam.algafood.exception.EntityInUseException;
import tech.blobteam.algafood.model.Kitchen;
import tech.blobteam.algafood.repository.kitchen.KitchenRepository;

import javax.persistence.EntityNotFoundException;

/** @author vinicius-victor */
@Service
@RequiredArgsConstructor
public class KitchenRegisterService {

  private final KitchenRepository repository;

  public Kitchen save(Kitchen kitchen) {
    return repository.save(kitchen);
  }

  public void delete(Long id) {
    try {
      repository.remove(id);
    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException("Kitchen cannot be removed because it's currently in use");
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException("Kitchen not found");
    }
  }
}
