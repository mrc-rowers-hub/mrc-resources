package com.codeaddi.mrc_resources.controller;

import com.codeaddi.mrc_resources.model.BoatTypeDetails;
import com.codeaddi.mrc_resources.model.enums.BoatType;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BoatConfig {

  private static final Map<BoatType, BoatTypeDetails> BOAT_TYPE_INFO =
      Map.of(
          BoatType.SINGLE_SCULL, new BoatTypeDetails("1x", 1, 2),
          BoatType.DOUBLE_SCULL, new BoatTypeDetails("2x", 2, 4),
          BoatType.COXLESS_QUAD, new BoatTypeDetails("4x", 4, 8),
          BoatType.COXED_QUAD, new BoatTypeDetails("4x+", 5, 8),
          BoatType.OCTUPLE, new BoatTypeDetails("8x+", 8, 16),
          BoatType.COXLESS_PAIR, new BoatTypeDetails("2-", 2, 2),
          BoatType.COXLESS_FOUR, new BoatTypeDetails("4-", 4, 4),
          BoatType.COXED_PAIR, new BoatTypeDetails("2+", 3, 2),
          BoatType.COXED_FOUR, new BoatTypeDetails("4+", 5, 4),
          BoatType.COXED_EIGHT, new BoatTypeDetails("8+", 8, 8));
}
