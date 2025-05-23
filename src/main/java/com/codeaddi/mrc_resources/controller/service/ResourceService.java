package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.BoatService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.controller.util.DateUtil;
import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.http.ResourceUseDTO;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.Boat;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;

import java.time.LocalTime;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceService { // Todo, maybe delete this one

    @Autowired
    BladeService bladeService;
    @Autowired
    BoatService boatService;
    @Autowired
    ResourceInUseService resourceInUseService;

// todo should the below live elsewhere?
    public ResponseEntity<?> getResourceInUseStatus(String date, String from, String to, EquipmentType equipmentType){
        Date dateParsed = DateUtil.getDateFromString(date);

        if (dateParsed == null) {
            log.warn("Invalid date passed: {}", date);
            return ResponseEntity.badRequest()
                    .body("Invalid/no date supplied. Please provide in the format dd/mm/yyyy");
        }
        if (from == null && to != null || to == null && from != null ){
            log.warn("Invalid timeframe passed: from {} to {}", from, to);
            return ResponseEntity.badRequest()
                    .body("Invalid/no timeframe supplied. Please BOTH from and to");
        }

        if (from == null && to == null) {
            log.info("Fetching all resources for date {}", dateParsed);
            return ResponseEntity.ok(getResourcesForDate(dateParsed, equipmentType));
        } else {
            LocalTime fromTime = (from != null) ? DateUtil.getTimeFromString(from) : null;
            LocalTime toTime = (to != null) ? DateUtil.getTimeFromString(to) : null;

            log.info("Fetching resources for date {}, from: {}, to: {}", dateParsed, fromTime, toTime);

            return ResponseEntity.ok(getResourcesForDateAndTime(dateParsed, fromTime, toTime, equipmentType));
        }
    }


    public List<ResourceUseDTO<Object>> getResourcesForDate(
            Date date, EquipmentType equipmentType) {

        List<?> allOfResource = equipmentType.equals(EquipmentType.BLADE) ? bladeService.getAllBlades() : boatService.getAllBoats();
        List<ResourceInUse> resourcesInUseOnDate = resourceInUseService.getAllResourceInUseForDate(date, equipmentType);
        return getResourceStatusDtos(allOfResource,resourcesInUseOnDate );
    }

    public List<ResourceUseDTO<Object>> getResourcesForDateAndTime(Date dateParsed, LocalTime fromTime, LocalTime toTime, EquipmentType equipmentType) {
        List<?> allOfResource = equipmentType.equals(EquipmentType.BLADE) ? bladeService.getAllBlades() : boatService.getAllBoats();

        List<ResourceInUse> allInUseWithinSession = getAllForResourceType(resourceInUseService.getAllResourcesInUseWithinTimePeriod(dateParsed, fromTime, toTime), equipmentType);
        return getResourceStatusDtos(allOfResource, allInUseWithinSession );
    }

    public List<ResourceInUse> getAllForResourceType(List<ResourceInUse> theList, EquipmentType equipmentType) {
        return theList.stream()
                .filter(resource -> resource.getEquipmentType() == equipmentType)
                .toList();
    }

    private List<ResourceUseDTO<Object>> getResourceStatusDtos( List<?> allResources, List<ResourceInUse> resourcesInUse){
        List<ResourceUseDTO<Object>> allResourcesOnDate = new ArrayList<>();

        for (Object resource : allResources) {
            Long id = getIdFromResource(resource);

            List<ResourceInUse> resourcesInUseOnDay =
                    getResourcesInUseForThisResource(id, resourcesInUse);
            allResourcesOnDate.add(
                    ResourceUseDTO.builder().resource(resource).inUseOnDate(resourcesInUseOnDay).build());
        }
        return allResourcesOnDate;
    }

    private static Long getIdFromResource(Object resource) {
        Long id = 0L;
        if (resource instanceof Blade) {
            id = ((Blade) resource).getId();
        } else if (resource instanceof Boat) {
            id = ((Boat) resource).getId();
        }
        return id;
    }

    private List<ResourceInUse> getResourcesInUseForThisResource(
            Long id, List<ResourceInUse> bladesInUseOnThisDate) {
        return bladesInUseOnThisDate.stream()
                .filter(resource -> resource.getResource_id().equals(id))
                .toList();
    }

}
