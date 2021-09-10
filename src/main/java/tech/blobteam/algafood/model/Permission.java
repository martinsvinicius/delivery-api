package tech.blobteam.algafood.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/** @author vinicius */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permission {
  @Id private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;
}
