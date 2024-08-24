package com.codeaddi.mrc_resources.controller.service.db;

import com.codeaddi.mrc_resources.model.repository.ResourceInUseRepository;
import com.codeaddi.mrc_resources.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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

        Date date = Date.from(TestData.instantNow);

        assertEquals(1, resourceInUseService.getAllBladesInUseForDate(date).size());
        assertEquals(TestData.ResourcesInUse.bladeResourceToday, resourceInUseService.getAllBladesInUseForDate(date).getFirst());

    }

}
