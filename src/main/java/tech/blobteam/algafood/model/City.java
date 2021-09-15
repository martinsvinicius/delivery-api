package tech.blobteam.algafood.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/** @author vinicius */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {
  @Id private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  private State state;
}
