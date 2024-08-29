package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import com.codeaddi.mrc_resources.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResourceServiceTests {

    @Autowired
    ResourceService resourceService;

    @Test
    void isInUseOnDate_matchingDates_returnsTrue() {
        assertTrue(resourceService.anyInUseOnDate(TestData.dateNow, TestData.ResourcesInUse.allResourcesInUse));
    }

    @Test
    void isInUseOnDate_nonMatchingDates_returnsFalse() {
        assertFalse(resourceService.anyInUseOnDate(TestData.dateNeverUsed, TestData.ResourcesInUse.allResourcesInUse));
    }

    @Test
    void getThoseInUseOnDate_todaysDateWithMatchingResources_returnsOnlyThoseForToday() {
      List<ResourceInUse> resourceInUseList =  resourceService.getThoseInUseOnDate(TestData.dateNow, TestData.ResourcesInUse.allResourcesInUse);

      assertEquals(2, resourceInUseList.size());

        for(ResourceInUse resource : resourceInUseList){
            assertTrue(resource.getDate().equals(TestData.dateNow));
        }
    }


}
