package com.codeaddi.mrc_resources.testUtils;

import com.codeaddi.mrc_resources.model.enums.BoatType;
import com.codeaddi.mrc_resources.model.enums.EquipmentStatus;
import com.codeaddi.mrc_resources.model.enums.RowerLevel;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.Boat;

public class TestData {
  public static Boat boat1 =
      Boat.builder()
          .avgCrewWeight(70)
          .boatType(BoatType.COXED_FOUR)
          .name("Ian Marr")
          .minimumRowerLevel(RowerLevel.NOVICE)
          .status(EquipmentStatus.WORKING)
          .build();
  public static Blade blade1 =
      Blade.builder().name("Purples").amount(8).status(EquipmentStatus.WORKING).build();
}
