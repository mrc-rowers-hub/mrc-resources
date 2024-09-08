package com.codeaddi.mrc_resources.controller.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.http.ResourceUseDTO;
import com.codeaddi.mrc_resources.testUtils.TestData;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ResourceServiceTests {

  @Autowired @InjectMocks ResourceService resourceService;

  @Mock private BladeService bladeService;
  @Mock ResourceInUseService resourceInUseService;

  @Test
  void getBladesForTime_partialBladesInUseOnDay_populatesExpectedForThoseInUse() {
    Date dateForUse = TestData.DatesAndTimes.dateNow;
    when(bladeService.getAllBlades())
        .thenReturn(List.of(TestData.orangeBlades, TestData.purpleBlades));
    when(resourceInUseService.getAllResourceInUseForDate(dateForUse, EquipmentType.BLADE))
        .thenReturn(List.of(TestData.ResourcesInUse.purpleBladeResourceToday));

    List<ResourceUseDTO<Object>> bladesForDate = resourceService.getBladesForDate(dateForUse, EquipmentType.BLADE);

    assertTrue(bladesForDate.size() == 2);

    for (ResourceUseDTO<Object> resourceUseDTO : bladesForDate) {
      if (resourceUseDTO.getResource().equals(TestData.orangeBlades)) {
        assertTrue(resourceUseDTO.getInUseOnDate().isEmpty());
      } else if (resourceUseDTO.getResource().equals(TestData.purpleBlades)) {
        assertFalse(resourceUseDTO.getInUseOnDate().isEmpty());
      } else {
        fail("Unexpected blade in list: " + resourceUseDTO.getResource());
      }
    }
  }
}
