package tech.blobteam.algafood.infra;

import org.springframework.transaction.annotation.Transactional;
import tech.blobteam.algafood.model.Kitchen;
import tech.blobteam.algafood.repository.KitchenRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/** @author vinicius */
public class KitchenRepositoryImpl implements KitchenRepository {
  @PersistenceContext private EntityManager manager;

  @Override
  public List<Kitchen> listAll() {
    TypedQuery<Kitchen> query = manager.createQuery("select k from Kitchen k", Kitchen.class);

    return query.getResultList();
  }

  @Override
  public Kitchen findById(Long id) {
    return manager.find(Kitchen.class, id);
  }

  @Override
  @Transactional
  public Kitchen save(Kitchen kitchen) {
    return manager.merge(kitchen);
  }

  @Override
  @Transactional
  public void remove(Kitchen kitchen) {
    Kitchen kitchenExists = findById(kitchen.getId());

    if (kitchenExists != null) {
      manager.remove(kitchenExists);
    }
  }
}
