package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ResourceServiceTests {

    @Autowired
    ResourceService resourceService;

    @Test
    void isInUseOnDate_matchingDates_returnsTrue(){
        assertTrue(resourceService.anyInUseOnDate(TestData.dateNow, TestData.ResourcesInUse.allResourcesInUse));
    }

    @Test
    void isInUseOnDate_nonMatchingDates_returnsFalse(){
        assertFalse(resourceService.anyInUseOnDate(TestData.dateNeverUsed, TestData.ResourcesInUse.allResourcesInUse));

    }



}
