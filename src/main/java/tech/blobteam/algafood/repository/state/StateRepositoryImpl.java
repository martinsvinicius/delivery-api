package tech.blobteam.algafood.repository.state;

import org.springframework.stereotype.Component;
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
  public State save(State state) {
    return manager.merge(state);
  }

  @Override
  public void remove(State state) {
    State stateExists = findById(state.getId());

    if (stateExists != null) {
      manager.remove(stateExists);
    }
  }
}
