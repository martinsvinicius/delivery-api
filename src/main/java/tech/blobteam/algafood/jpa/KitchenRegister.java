package tech.blobteam.algafood.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.blobteam.algafood.model.Kitchen;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class KitchenRegister {

  @PersistenceContext private EntityManager manager;

  public List<Kitchen> list() {
    TypedQuery<Kitchen> query = manager.createQuery("select k from Kitchen k", Kitchen.class);

    return query.getResultList();
  }

  @Transactional
  public Kitchen save(Kitchen kitchen) {
    return manager.merge(kitchen);
  }
}
