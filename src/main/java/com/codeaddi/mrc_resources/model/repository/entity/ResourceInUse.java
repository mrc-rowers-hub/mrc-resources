package com.codeaddi.mrc_resources.model.repository.entity;

import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import lombok.*;

@Entity
@Table(name = "resources_in_use")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceInUse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "id")
  private Long resource_id; // for the boat, or blade

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private EquipmentType equipmentType;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "upcoming_session_id")
  private Long upcomingSessionId;

  @Column(name = "date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date date;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;
}
