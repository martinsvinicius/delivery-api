package tech.blobteam.algafood.repository.restaurant;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.blobteam.algafood.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/** @author vinicius */
@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<Restaurant> listAll() {
    TypedQuery<Restaurant> query =
        manager.createQuery("select r from Restaurant r", Restaurant.class);

    return query.getResultList();
  }

  @Override
  public Restaurant findById(Long id) {
    return manager.find(Restaurant.class, id);
  }

  @Override
  @Transactional
  public Restaurant save(Restaurant restaurant) {
    return manager.merge(restaurant);
  }

  @Override
  @Transactional
  public void remove(Long id) {
    Restaurant restaurantExists = findById(id);

    if (restaurantExists == null) throw new EmptyResultDataAccessException(1);

    manager.remove(restaurantExists);
  }
}
