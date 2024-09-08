package com.codeaddi.mrc_resources.controller.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.model.http.ResourceUseDTO;
import com.codeaddi.mrc_resources.model.repository.ResourceInUseRepository;
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

  @Autowired
  @InjectMocks
  ResourceService resourceService;

  @Mock
  private ResourceInUseRepository resourceInUseRepository;
  @Mock
  private BladeService bladeService;
  @Mock
  ResourceInUseService resourceInUseService;





  @Test
  void getBladesForTime_partialBladesInUseOnDay_populatesExpectedForThoseInUse() {
    Date dateForUse = TestData.DatesAndTimes.dateNow;
    when(bladeService.getAllBlades()).thenReturn(List.of(TestData.orangeBlades, TestData.purpleBlades));
    when(resourceInUseService.getAllBladesInUseForDate(dateForUse)).thenReturn(List.of(TestData.ResourcesInUse.purpleBladeResourceToday));

    List<ResourceUseDTO<Object>> bladesForDate = resourceService.getBladesForDate(dateForUse);

    assertTrue(bladesForDate.size() == 2);

    ResourceUseDTO<Object> purplesExpected = ResourceUseDTO.builder().resource(TestData.purpleBlades).inUseOnDate(List.of(TestData.ResourcesInUse.purpleBladeResourceToday)).build();
    ResourceUseDTO<Object> orangesExpected = ResourceUseDTO.builder().resource(TestData.orangeBlades).build();

    for(ResourceUseDTO<Object> resourceUseDTO : bladesForDate){
      if(resourceUseDTO.getResource().equals(TestData.orangeBlades)){
        assertTrue(resourceUseDTO.getInUseOnDate().isEmpty());
      } else if (resourceUseDTO.getResource().equals(TestData.purpleBlades)) {
        assertFalse(resourceUseDTO.getInUseOnDate().isEmpty());
      } else {
        fail("Unexpected blade in list: " + resourceUseDTO.getResource());
      }
    }

  }

//  @BeforeEach
//  public void setUp() {
//    resourceService = new ResourceService(); // assuming this contains your method
//    resourceInUse = mock(ResourceInUse.class); // mock the ResourceInUse class
//  }


  // old below
//
//  @Test
//  void getThoseInUseOnDate_todaysDateWithMatchingResources_returnsOnlyThoseForToday() {
//    List<ResourceInUse> resourceInUseList =
//            resourceService.getThoseInUseOnDate(
//                    TestData.DatesAndTimes.dateNow, TestData.ResourcesInUse.allResourcesInUse);
//
//    assertEquals(2, resourceInUseList.size());
//
//    for (ResourceInUse resource : resourceInUseList) {
//      assertTrue(resource.getDate().equals(TestData.DatesAndTimes.dateNow));
//    }
//  }
//
//  @Test
//  void resourceStartAfter2HoursOREndBeforeTimeWanted_resourceStartsAfterSession_isTrue() {
//    LocalTime sessionStart = TestData.DatesAndTimes.am8;
//    LocalTime sessionEnd = TestData.DatesAndTimes.am959;
//    LocalTime resourceStart = TestData.DatesAndTimes.am10;
//    LocalTime resourceEnd = TestData.DatesAndTimes.pm12;
//    assertTrue(resourceService.resourceStartAfter2HoursOREndBeforeTimeWanted(sessionStart, sessionEnd, resourceStart, resourceEnd));
//  }
//
//  @Test
//  void resourceStartAfter2HoursOREndBeforeTimeWanted_resourceEndsBeforeSession_isTrue() {
//    LocalTime sessionStart = TestData.DatesAndTimes.am10;
//    LocalTime sessionEnd = TestData.DatesAndTimes.pm12;
//    LocalTime resourceStart = TestData.DatesAndTimes.am8;
//    LocalTime resourceEnd = TestData.DatesAndTimes.am959;
//    assertTrue(resourceService.resourceStartAfter2HoursOREndBeforeTimeWanted(sessionStart, sessionEnd, resourceStart, resourceEnd));
//  }
//
//  @Test
//  void resourceStartAfter2HoursOREndBeforeTimeWanted_resourceStartsDuringSession_isFalse() {
//    LocalTime sessionStart = TestData.DatesAndTimes.am10;
//    LocalTime sessionEnd = TestData.DatesAndTimes.pm12;
//    LocalTime resourceStart = TestData.DatesAndTimes.am10;
//    LocalTime resourceEnd = TestData.DatesAndTimes.pm12;
//    assertFalse(resourceService.resourceStartAfter2HoursOREndBeforeTimeWanted(sessionStart, sessionEnd, resourceStart, resourceEnd));
//  }
//
//  @Test
//  void resourceStartBeforeSessionStartAndEndAfterSessionEnd_resourceStartsDuringSession_isFalse() {
//    LocalTime sessionStart = TestData.DatesAndTimes.am8;
//    LocalTime sessionEnd = TestData.DatesAndTimes.pm12;
//    LocalTime resourceStart = TestData.DatesAndTimes.am959;
//    LocalTime resourceEnd = TestData.DatesAndTimes.am10;
//    assertFalse(resourceService.resourceStartBeforeSessionStartAndEndAfterSessionEnd(sessionStart, sessionEnd, resourceStart, resourceEnd));
//  }
//
//  @Test
//  void resourceStartBeforeSessionStartAndEndAfterSessionEnd_resourceStartsBeforeAndEndsAfterSession_isTrue() {
//    LocalTime sessionStart = TestData.DatesAndTimes.am10;
//    LocalTime sessionEnd = TestData.DatesAndTimes.pm12;
//    LocalTime resourceStart = TestData.DatesAndTimes.am8;
//    LocalTime resourceEnd = TestData.DatesAndTimes.pm1201;
//    assertTrue(resourceService.resourceStartBeforeSessionStartAndEndAfterSessionEnd(sessionStart, sessionEnd, resourceStart, resourceEnd));
//  }
//
//  @Disabled("broken atm")
//  @Test
//  void getResourceStatusForResourceFromTime_resourceInUseAllSession_returnsUSED_ALL_SESSION(){
//
//    when(resourceInUse.getStartTime()).thenReturn(TestData.DatesAndTimes.am10);
//    when(resourceInUse.getEndTime()).thenReturn(TestData.DatesAndTimes.pm12);
//
//    // Call the method
//    ResourceStatus result = resourceService.getResourceStatusForResourceFromTime(resourceInUse, TestData.DatesAndTimes.am10);
//
//    // Assert expected outcome
//    assertEquals(ResourceStatus.USED_ALL_SESSION, result);
//  }


}
