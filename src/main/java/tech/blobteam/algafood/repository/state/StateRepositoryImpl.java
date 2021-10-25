package tech.blobteam.algafood.repository.state;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.blobteam.algafood.model.State;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/** @author vinicius-victor */
@Component
public class StateRepositoryImpl implements StateRepository {
  @PersistenceContext EntityManager manager;

  @Override
  public List<State> listAll() {
    TypedQuery<State> query = manager.createQuery("select s from State s", State.class);

    return query.getResultList();
  }

  @Override
  public State findById(Long id) {
    return manager.find(State.class, id);
  }

  @Override
  @Transactional
  public State save(State state) {
    return manager.merge(state);
  }

  @Override
  @Transactional
  public void remove(Long id) {
    State stateExists = findById(id);

    if (stateExists == null) throw new EmptyResultDataAccessException(1);

    manager.remove(stateExists);
  }
}
