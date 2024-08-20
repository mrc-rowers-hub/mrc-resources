package com.codeaddi.mrc_resources.testUtils;

import com.codeaddi.mrc_resources.model.entity.Boat;
import com.codeaddi.mrc_resources.model.enums.BoatType;
import com.codeaddi.mrc_resources.model.enums.EquipmentStatus;
import com.codeaddi.mrc_resources.model.enums.RowerLevel;

public class TestData {
  public static Boat boat1 =
      Boat.builder()
          .avgCrewWeight(70)
          .boatType(BoatType.COXED_FOUR)
          .name("Ian Marr")
          .minimumRowerLevel(RowerLevel.NOVICE)
          .status(EquipmentStatus.WORKING)
          .build();
}
