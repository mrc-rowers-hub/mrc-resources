package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.controller.util.DateUtil;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResourceService {

    @Autowired
    BladeService bladeService;

    @Autowired
    ResourceInUseService resourceInUseService;

//    public List<Blade> getBladesAvailableAtTime(LocalDateTime localDateTime){
    public void getBladesAvailableAtTime(LocalDateTime localDateTime){
        Map<Date, LocalTime> dateTime = DateUtil.getDateAndTimeFromLocalDateTime(localDateTime);
        Date date = dateTime.keySet().stream().findAny().orElseThrow();
       LocalTime localTime = dateTime.get(date);

        List<Blade> allBlades = bladeService.getAllBlades();
        List<ResourceInUse> bladesInUse = resourceInUseService.getAllBladesInUseForDate(date);
    }

    public boolean isInUseOnDate(Date date, List<ResourceInUse> bladesInUse){
        return bladesInUse.stream().anyMatch(blade -> blade.getDate().equals(date));
    }


}
