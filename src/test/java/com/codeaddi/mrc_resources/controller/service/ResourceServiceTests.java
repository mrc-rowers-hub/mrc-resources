package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
        assertTrue(resourceService.isInUseOnDate(TestData.dateNow, TestData.ResourcesInUse.allResourcesInUse));
    }

    @Test
    void isInUseOnDate_nonMatchingDates_returnsFalse(){
        assertFalse(resourceService.isInUseOnDate(TestData.dateNeverUsed, TestData.ResourcesInUse.allResourcesInUse));

    }



}
