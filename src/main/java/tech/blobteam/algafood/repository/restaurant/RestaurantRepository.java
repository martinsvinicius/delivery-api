package tech.blobteam.algafood.repository.restaurant;

import tech.blobteam.algafood.model.Restaurant;

import java.util.List;

/** @author vinicius */
public interface RestaurantRepository {
  List<Restaurant> listAll();

  Restaurant findById(Long id);

  Restaurant save(Restaurant restaurant);

  void remove(Long id);
}
