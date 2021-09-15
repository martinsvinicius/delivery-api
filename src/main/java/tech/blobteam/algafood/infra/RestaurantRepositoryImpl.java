package tech.blobteam.algafood.infra;

import org.springframework.transaction.annotation.Transactional;
import tech.blobteam.algafood.model.Restaurant;
import tech.blobteam.algafood.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/** @author vinicius */
public class RestaurantRepositoryImpl implements RestaurantRepository {

  @PersistenceContext EntityManager manager;

  @Override
  public List<Restaurant> listAll() {
    TypedQuery<Restaurant> query = manager.createQuery("select r from Restaurant r", Restaurant.class);

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
  public void remove(Restaurant restaurant) {
    Restaurant restaurantExists = findById(restaurant.getId());

    if (restaurantExists != null) {
      manager.remove(restaurant);
    }
  }
}
