package tech.blobteam.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import tech.blobteam.algafood.AlgafoodApplication;
import tech.blobteam.algafood.model.Kitchen;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new SpringApplicationBuilder(AlgafoodApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    KitchenRegister kitchenRegister = applicationContext.getBean(KitchenRegister.class);

    //list
    List<Kitchen> kitchens = kitchenRegister.list();

    kitchens.forEach(kitchen -> System.out.println(kitchen.getName()));


    //create
    Kitchen newKitchen = new Kitchen();
    newKitchen.setName("Japonesa");

    Kitchen kitchen = kitchenRegister.save(newKitchen);

    System.out.println("Nova cozinha adicionada: " + kitchen.getName());

    //update
    Kitchen kitchenToUpdate = new Kitchen();
    kitchenToUpdate.setId(1L);
    kitchenToUpdate.setName("Brasileira");

    kitchenRegister.save(kitchenToUpdate);
  }
}
