package tech.blobteam.algafood.repository.state;

import tech.blobteam.algafood.model.State;

import java.util.List;

/** @author vinicius-victor */
public interface StateRepository {
  List<State> listAll();

  State findById(Long id);

  State save(State state);

  void remove(State state);
}
