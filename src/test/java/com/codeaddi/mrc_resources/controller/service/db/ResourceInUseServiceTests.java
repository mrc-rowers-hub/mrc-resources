package com.codeaddi.mrc_resources.controller.service.db;

import com.codeaddi.mrc_resources.model.repository.ResourceInUseRepository;
import com.codeaddi.mrc_resources.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ResourceInUseServiceTests {
    @Mock
    ResourceInUseRepository resourceInUseRepository;

    @InjectMocks
    ResourceInUseService resourceInUseService;

    @Test
    void getAllBladesInUseForDate_todaysDate_givesAllForToday(){
        when(resourceInUseRepository.findAll()).thenReturn(TestData.ResourcesInUse.allResourcesInUse);


        assertEquals(1, resourceInUseService.getAllBladesInUseForDate(TestData.dateNow).size());
        assertEquals(TestData.ResourcesInUse.bladeResourceToday, resourceInUseService.getAllBladesInUseForDate(TestData.dateNow).getFirst());
    }

    @Test
    void getAllBoatsInUseForDate_todaysDate_givesAllForToday(){
        when(resourceInUseRepository.findAll()).thenReturn(TestData.ResourcesInUse.allResourcesInUse);

        assertEquals(1, resourceInUseService.getAllBladesInUseForDate(TestData.dateNow).size());
        assertEquals(TestData.ResourcesInUse.boatResourceToday, resourceInUseService.getAllBoatsInUseForDate(TestData.dateNow).getFirst());
    }


}