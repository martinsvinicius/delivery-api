package tech.blobteam.algafood.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/** @author vinicius */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class State {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String name;
}
