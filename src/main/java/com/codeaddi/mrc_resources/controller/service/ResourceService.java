package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.controller.util.DateUtil;
import com.codeaddi.mrc_resources.model.http.enums.ResourceStatus;
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
    // change to be 'getResourceAvailableAtTime' and then have a method for filtering boats and blades
// Todo in here
    public void getBladesAvailableAtTime(LocalDateTime localDateTime){
        Map<Date, LocalTime> dateTime = DateUtil.getDateAndTimeFromLocalDateTime(localDateTime);
        Date date = dateTime.keySet().stream().findAny().orElseThrow();
       LocalTime localTime = dateTime.get(date);

        List<Blade> allBlades = bladeService.getAllBlades();
        List<ResourceInUse> bladesInUse = resourceInUseService.getAllBladesInUseForDate(date);

        if(anyInUseOnDate(date, bladesInUse)){
            // find out the times
            // if that is within 2 hours of this session, return notification of
        } else {

        }
    }

    public boolean anyInUseOnDate(Date date, List<ResourceInUse> bladesInUse){
        return bladesInUse.stream().anyMatch(blade -> blade.getDate().equals(date));
    }

    public List<ResourceInUse> getThoseInUseOnDate(Date date, List<ResourceInUse> bladesInUse){
        return bladesInUse.stream().filter(blade -> blade.getDate().equals(date)).toList();
    }
//
//    public List<ResourceUseDTO> getResourcesInUseDTOForAvailableBlades(List<Blade> blades){
//
//    }

    public ResourceStatus getResourceStatus(){
        return null;
    }


}
