package com.codeaddi.mrc_resources.model.repository;

import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.time.LocalTime;

public interface ResourceInUseRepository extends JpaRepository<ResourceInUse, Long> {
    List<ResourceInUse> findByDateAndStartTimeBefore(Date date, LocalTime startTime);
    List<ResourceInUse> findByDateAndEndTimeAfter(Date date, LocalTime endTime);
}
