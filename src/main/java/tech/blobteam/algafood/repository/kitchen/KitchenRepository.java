package tech.blobteam.algafood.repository.kitchen;

import org.springframework.stereotype.Component;
import tech.blobteam.algafood.model.Kitchen;

import java.util.List;

/** @author vinicius-martins */
public interface KitchenRepository {
  List<Kitchen> listAll();

  Kitchen findById(Long id);

  Kitchen save(Kitchen kitchen);

  void remove(Kitchen kitchen);
}
