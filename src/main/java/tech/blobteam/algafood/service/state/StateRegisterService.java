package tech.blobteam.algafood.service.state;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tech.blobteam.algafood.exception.EntityInUseException;
import tech.blobteam.algafood.model.State;
import tech.blobteam.algafood.repository.state.StateRepository;

import javax.persistence.EntityNotFoundException;

/** @author vinicius-victor */
@Service
@RequiredArgsConstructor
public class StateRegisterService {

  private final StateRepository repository;

  public State save(State state) {
    return repository.save(state);
  }

  public void delete(Long id) {
    try {
      repository.remove(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException("State not found");
    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException("State cannot be removed because it's currently in use");
    }
  }
}
