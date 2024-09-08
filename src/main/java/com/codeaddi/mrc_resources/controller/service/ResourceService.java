package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.controller.util.DateUtil;
import com.codeaddi.mrc_resources.model.http.ResourceUseDTO;
import com.codeaddi.mrc_resources.model.http.enums.ResourceStatus;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceService { // Todo, maybe delete this one
// Todo, adjust access modifiers and remove temp tests
    private static LocalTime SESSION_DURATION = LocalTime.of(2, 0); // Todo have this in config


    public enum InUseStatus {
        IN_USE_ON_DATE,
        NOT_IN_USE_ON_DATE
    }

    @Autowired
    BladeService bladeService;

    @Autowired
    ResourceInUseService resourceInUseService;


    /// NEW STUFF
    public List<ResourceUseDTO<Object>> getBladesForDate(Date date){ // Todo take in type opf resource and filter by that

        List<Blade> allBlades = bladeService.getAllBlades();
        List<ResourceInUse> bladesInUseOnThisDate = resourceInUseService.getAllBladesInUseForDate(date);

        List<ResourceUseDTO<Object>> allBladesOnDate = new ArrayList<>();

        for(Blade blade : allBlades){
            List<ResourceInUse> resourcesInUseOnDay =  getResourcesInUseForThisBlade(blade, bladesInUseOnThisDate);
            allBladesOnDate.add(ResourceUseDTO.builder().resource(blade).inUseOnDate(resourcesInUseOnDay).build());

        }
        return allBladesOnDate;
    }

    private List<ResourceInUse> getResourcesInUseForThisBlade(Blade blade, List<ResourceInUse> bladesInUseOnThisDate){
       return  bladesInUseOnThisDate.stream().filter(resource -> resource.getResource_id().equals(blade.getId())).toList();
    }









    //// OLD BELOW

    //    public List<Blade> getBladesAvailableAtTime(LocalDateTime localDateTime){
    // change to be 'getResourceAvailableAtTime' and then have a method for filtering boats and blades
    // Todo in here
    public void getBladeAvailabilityForDateTime(LocalDateTime localDateTime) {
        Map<Date, LocalTime> dateTime = DateUtil.getDateAndTimeFromLocalDateTime(localDateTime);
        Date date = dateTime.keySet().stream().findAny().orElseThrow();
        LocalTime localTime = dateTime.get(date);

        List<Blade> allBlades = bladeService.getAllBlades();
        List<ResourceInUse> bladesInUseOnThisDate = resourceInUseService.getAllBladesInUseForDate(date);

        List<Blade> bladesNotInUseOnDate = getBladesNotInUseOnDate(bladesInUseOnThisDate, allBlades);

        Map<Blade, ResourceStatus> bladeResourceStatusMap = new HashMap<>();

        for (Blade blade : bladesNotInUseOnDate) {
            bladeResourceStatusMap.put(blade, ResourceStatus.NOT_IN_USE_ON_DAY);
        }


    }

    //Todo standardise the method names here, all shjould be for a resource, and filter for boat/blade depending on the inputs perhaps?
    private List<Blade> getBladesNotInUseOnDate(List<ResourceInUse> bladesInUse, List<Blade> allBlades) {
        List<Long> allBladeIds = allBlades.stream().map(Blade::getId).toList();
        List<Long> allBladesInUseIds = bladesInUse.stream().map(ResourceInUse::getResource_id).toList();

        List<Blade> bladesNotInUse = new ArrayList<>();

        for (Long bladeId : allBladeIds) {
            if (!allBladesInUseIds.contains(bladeId)) {
                // hmmm, but what about the quantity
                bladesNotInUse.add(getBladeById(bladeId, allBlades));
            }
        }

        return bladesNotInUse;
    }

    private Blade convertResourceToBlade(ResourceInUse resource, List<Blade> allBlades) {
        try {
            return allBlades.stream().filter(blade -> blade.getId().equals(resource.getResource_id())).findFirst().orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Blade getBladeById(Long bladeId, List<Blade> allBlades) {
        try {
            return allBlades.stream().filter(blade -> blade.getId().equals(bladeId)).findFirst().orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResourceInUse> getThoseInUseOnDate(Date date, List<ResourceInUse> bladesInUse) {
        return bladesInUse.stream().filter(blade -> blade.getDate().equals(date)).toList();
    }

    public void getResourceStatusForThoseInUse(List<ResourceInUse> bladesForDate, LocalTime timeWanted) {
        for (ResourceInUse resource : bladesForDate) {
            getResourceStatusForResourceFromTime(resource, timeWanted);
        }
    }

    public ResourceStatus getResourceStatusForResourceFromTime(ResourceInUse resource, LocalTime timeWanted) {
        // shuold be private after testing
// Todo make this into case / switch
        LocalTime timeWantedPlusOneHourFiftyNine = getTimePlusTwoHours(timeWanted);
        log.info("ADDI {}", timeWantedPlusOneHourFiftyNine);
        LocalTime startTimeOfResource = resource.getStartTime();
        LocalTime endTimeOfResource = resource.getEndTime();

        if (resourceStartAfter2HoursOREndBeforeTimeWanted(timeWanted, timeWantedPlusOneHourFiftyNine, startTimeOfResource, endTimeOfResource)) {
            return ResourceStatus.IN_USE_OD_NOT_WITHIN_2_HOURS;
        } else if (resourceStartBeforeSessionStartAndEndAfterSessionEnd(timeWanted, timeWantedPlusOneHourFiftyNine, startTimeOfResource, endTimeOfResource)) {
            return ResourceStatus.USED_ALL_SESSION;
        } else {
            return ResourceStatus.PARTIAL_USE_WITHIN_SESSION;
        }
    }

    public boolean resourceStartAfter2HoursOREndBeforeTimeWanted(LocalTime sessionStartTime, LocalTime sessionEndTime, LocalTime startTimeOfResource, LocalTime endTimeOfResource) {
        return startTimeOfResource.isAfter(sessionEndTime) || endTimeOfResource.isBefore(sessionStartTime);
    }

    public boolean resourceStartBeforeSessionStartAndEndAfterSessionEnd(LocalTime sessionStartTime, LocalTime sessionEndTime, LocalTime startTimeOfResource, LocalTime endTimeOfResource){
        return (startTimeOfResource.isBefore(sessionStartTime) || startTimeOfResource.equals(sessionEndTime)) && (endTimeOfResource.equals(sessionEndTime) || endTimeOfResource.isAfter(sessionEndTime));
    }

    private LocalTime getTimePlusTwoHours(LocalTime time) {
        return time.plus(SESSION_DURATION.getHour(), ChronoUnit.HOURS).minusMinutes(1);
    }
}
