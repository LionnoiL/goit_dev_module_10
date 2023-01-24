package ua.gaponov.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Planet {

  @Id
  @Column(nullable = false, length = 50)
  private String id;

  @Column(length = 500)
  private String name;
}
