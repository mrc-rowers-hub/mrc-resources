package com.codeaddi.mrc_resources.model.repository.entity;

import com.codeaddi.mrc_resources.model.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blades")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "amount")
  private int amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EquipmentStatus status;
}
