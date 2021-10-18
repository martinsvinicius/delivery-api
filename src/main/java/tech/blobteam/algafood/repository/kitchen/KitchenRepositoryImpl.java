package tech.blobteam.algafood.repository.kitchen;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.blobteam.algafood.model.Kitchen;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/** @author vinicius */
@Component
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
