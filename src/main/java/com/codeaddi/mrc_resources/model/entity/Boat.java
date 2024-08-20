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
    private String avgCrewWeight;

    @Column(name = "boat_type")
    private BoatType boatType;

    @Column(name = "minimum_rower_level")
    private RowerLevel minimumRowerLevel;

    @Column(name = "status")
    private EquipmentStatus status;

    @Column(name = "best_blades_id")
    private Long bestBladesId;

}