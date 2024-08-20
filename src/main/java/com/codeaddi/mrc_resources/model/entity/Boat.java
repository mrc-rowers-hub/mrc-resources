package com.codeaddi.mrc_resources.model.entity;

import com.codeaddi.mrc_resources.model.enums.BoatType;
import com.codeaddi.mrc_resources.model.enums.EquipmentStatus;
import com.codeaddi.mrc_resources.model.enums.RowerLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boats")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "avg_crew_weight")
  private float avgCrewWeight;

  @Enumerated(EnumType.STRING)
  @Column(name = "boat_type")
  private BoatType boatType;

  @Enumerated(EnumType.STRING)
  @Column(name = "minimum_rower_level")
  private RowerLevel minimumRowerLevel;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EquipmentStatus status;

  @Column(name = "best_blades_id")
  private Long bestBladesId;
}
